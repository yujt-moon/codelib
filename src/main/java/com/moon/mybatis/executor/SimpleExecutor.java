package com.moon.mybatis.executor;

import com.moon.mybatis.config.Configuration;
import com.moon.mybatis.config.MappedStatement;
import com.moon.mybatis.log.Log;
import com.moon.mybatis.log.LogFactory;
import com.moon.mybatis.mapping.StatementType;
import com.moon.mybatis.sqlsource.BoundSql;
import com.moon.mybatis.sqlsource.ParameterMapping;
import com.moon.mybatis.sqlsource.SqlSource;
import com.moon.mybatis.transaction.Transaction;
import com.moon.mybatis.util.CollectionUtils;
import com.moon.mybatis.util.OgnlUtils;
import com.moon.util.StringUtils;

import javax.sql.DataSource;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 普通执行 JDBC 程序
 *
 * @author yujiangtao
 * @date 2020/8/7 上午9:39
 */
public class SimpleExecutor extends BaseExecutor {

    protected final Log log = LogFactory.getLog(getClass());

    private Log sqlLog;

    public SimpleExecutor(Configuration configuration, Transaction transaction) {
        super(configuration, transaction);
    }

    @Override
    public <T> List<T> queryFromDataBase(MappedStatement mappedStatement, Configuration configuration, Object param) {
        List<T> results = new ArrayList<>();
        sqlLog = LogFactory.getLog(mappedStatement.getStatementId());

        try {
            // 获取连接
            Connection connection = getConnection(configuration);
            // 获取 sql 语句
            BoundSql boundSql = getBoundSql(mappedStatement.getSqlSource(), param);
            sqlLog.debug("==> Preparing: " + boundSql.getSql());

            StatementType statementType = mappedStatement.getStatementType();

            // 使用 mybatis 的四大组件
            if (statementType == StatementType.PREPARED) {
                // 创建 Statement
                PreparedStatement statement = createStatement(connection, mappedStatement, boundSql.getSql());
                // 设置参数
                handlerParameter(statement, boundSql, param);
                // 执行 Statement
                ResultSet resultSet = statement.executeQuery();
                // 处理结果
                handlerResult(resultSet, mappedStatement, results);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return results;
    }

    private <T> void handlerResult(ResultSet rs, MappedStatement mappedStatement, List<T> results) throws Exception {
        // 从结果集一行一行的取数据
        // 每一行数据,在一列一列的取数据(包括列的名称和列的值)
        // 最终获取到的每一列的值都映射到目标对象的指定属性中(列的名称和属性名称要一致)
        Class<?> resultTypeClass = mappedStatement.getResultTypeClass();
        int resultCount = 0;
        StringBuffer sb = new StringBuffer();
        List<Field> fields = new ArrayList<>();
        while(rs.next()) {
            resultCount++;
            // 要映射的结果目标对象
            T result = (T) resultTypeClass.newInstance();
            // 获取结果集的元数据(目的是取列的信息)
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            if(resultCount == 1) {
                sb.append("<==\tColumns: ");
            }
            for (int i = 0; i < columnCount; i++) {
                // 获取标签名称 author_id as author，选择 author
                String columnName = metaData.getColumnLabel(i + 1);
                if(resultCount == 1) {
                    sb.append(columnName + ", ");
                }
                String fieldName = StringUtils.toCamelFormat(columnName);
                Field field = null;
                try {
                    field = resultTypeClass.getDeclaredField(fieldName);
                } catch (NoSuchFieldException e) {
                    // 无法匹配该字段,忽略
                }
                if(field != null) {
                    field.setAccessible(true);
                    field.set(result, rs.getObject(columnName));
                    if(resultCount == 1) {
                        fields.add(field);
                    }
                }
            }

            results.add(result);
        }
        sqlLog.debug("<== Total: " + resultCount);
        sqlLog.debug(sb.delete(sb.length() - 2, sb.length() -1).toString());
        logRowsInfo(results, fields);
    }

    /**
     * 打印每行查询的结果信息
     * @param results
     * @param fields
     * @param <T>
     */
    private <T> void logRowsInfo(List<T> results, List<Field> fields) {
        if(CollectionUtils.isNotEmpty(results)) {
            for (T result : results) {
                StringBuffer sb = new StringBuffer();
                sb.append("<==\t    Row: ");
                for (Field field : fields) {
                    try {
                        Object o = field.get(result);
                        if(o == null) {
                            sb.append("null, ");
                        } else {
                            sb.append(o.toString() + ", ");
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                sb.delete(sb.length() - 2, sb.length() - 1);
                sqlLog.debug(sb.toString());
            }
        }
    }

    private void handlerParameter(PreparedStatement statement, BoundSql boundSql, Object param) throws Exception {
        List<Object> parameters = new ArrayList<>();
        // 判断入参的类型,如果是简单类型,直接处理
        if(param instanceof Integer) {
            statement.setObject(1, Integer.parseInt(param.toString()));
            parameters.add(Integer.parseInt(param.toString()));
        } else if(param instanceof String) {
            statement.setObject(1, param.toString());
            parameters.add(param.toString());
        } else if(param.getClass().isArray()) {
            // 如果当前对象是一个数组
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            int size = Array.getLength(param);
            for (int i = 0; i < parameterMappings.size(); i++) {
                Object valueToUse = Array.get(param, i);
                statement.setObject(i+1, valueToUse);
                parameters.add(valueToUse);
            }
        } else if(param instanceof Collection) {
            // 当前对象是一个集合,包括(List|Set)
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            for (int i = 0; i < parameterMappings.size(); i++) {
                // 通过 ognl 表达式获取值
                Object valueToUse = OgnlUtils.getValue(parameterMappings.get(i).getName(), param);
                statement.setObject(i+1, valueToUse);
                parameters.add(valueToUse);
            }
        } else {
            // 如果是 POJO 类型,则根据参数信息里面的参数名称,去入参对象中获取对应的参数值
            // 获取参数集合信息(#{}处理之后得到的参数信息)
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            for (int i = 0; i < parameterMappings.size(); i++) {
                Object valueToUse = null;
                ParameterMapping parameterMapping = parameterMappings.get(i);
                // #{} 中的参数名称,也应该和 POJO 类型中的属性名称一致
                String name = parameterMapping.getName();
                // 使用反射获取指定 name 的值
                Class<?> clazz = param.getClass();
                // 获取
                Field field = clazz.getDeclaredField(name);
                field.setAccessible(true);
                valueToUse = field.get(param);
                statement.setObject(i+1, valueToUse);
                parameters.add(valueToUse);
            }
        }
        if(CollectionUtils.isNotEmpty(parameters)) {
            StringBuilder sb = new StringBuilder();
            parameters.stream().forEach(parameter -> {
                sb.append(parameter + "(" + parameter.getClass().getSimpleName() + ")" + " ");
            });
            sqlLog.debug("==> Parameters: " + sb.toString());
        }
    }

    private PreparedStatement createStatement(Connection connection, MappedStatement mappedStatement, String sql) throws SQLException {
        if(mappedStatement.isUseGeneratedKeys()) {
            return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        }
        return connection.prepareStatement(sql);
    }

    private BoundSql getBoundSql(SqlSource sqlSource, Object param) {
        return sqlSource.getBoundSql(param);
    }

    private Connection getConnection(Configuration configuration) throws SQLException {
        DataSource dataSource = configuration.getDataSource();
        return dataSource.getConnection();
    }

    @Override
    public int doUpdate(MappedStatement mappedStatement, Configuration configuration, Object param) {
        sqlLog = LogFactory.getLog(mappedStatement.getStatementId());
        try {
            Connection connection = getConnection(configuration);
            // 获取 sql 语句
            BoundSql boundSql = getBoundSql(mappedStatement.getSqlSource(), param);
            sqlLog.debug("==> Preparing: " + boundSql.getSql());
            PreparedStatement statement = createStatement(connection, mappedStatement,
                    boundSql.getSql());
            handlerParameter(statement, mappedStatement.getSqlSource().getBoundSql(param), param);
            int rows = statement.executeUpdate();
            if(mappedStatement.isUseGeneratedKeys()) {
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    sqlLog.debug("==> generatedKeys: " + id);
                    String idName = mappedStatement.getKeyProperty();
                    BeanInfo beanInfo = Introspector.getBeanInfo(param.getClass());
                    PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
                    for (PropertyDescriptor pd : pds) {
                        if(pd.getName().equals(idName)) {
                            pd.getWriteMethod().invoke(param, id);
                            break;
                        }
                    }
                }
            }
            sqlLog.debug("==> total: " + rows);
            return rows;
        } catch (Exception e) {
            log.error(e.toString());
            e.printStackTrace();
        }
        return 0;
    }
}
