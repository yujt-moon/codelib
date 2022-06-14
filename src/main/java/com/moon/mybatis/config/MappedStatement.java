package com.moon.mybatis.config;

import com.moon.mybatis.mapping.SqlCommandType;
import com.moon.mybatis.mapping.StatementType;
import com.moon.mybatis.sqlsource.SqlSource;

/**
 * 封装 select 等 CRUD 标签的信息
 *
 * @author yujiangtao
 * @date 2020/8/3 下午8:56
 */
public class MappedStatement {

    /**
     * 命名空间
     */
    private String namespace;

    /**
     * id
     */
    private String id;

    /**
     * namespace + "." + id
     */
    private String statementId;

    /**
     * 参数类型
     */
    private Class<?> parameterTypeClass;

    /**
     * 结果类型
     */
    private Class<?> resultTypeClass;

    private String resultMapId;

    private StatementType statementType;

    private SqlCommandType sqlCommandType;

    private SqlSource sqlSource;

    private Configuration configuration;

    /**
     * 是否使用自增的主键
     */
    private boolean useGeneratedKeys = false;

    /**
     * 主键的名称（useGeneratedKeys 为 true 的时候才起效）
     */
    private String keyProperty;

    private MappedStatement() {}

    public static class Builder {
        private MappedStatement mappedStatement = new MappedStatement();

        /**
         * 必填参数通过构造函数注入
         * @param configuration
         * @param sqlCommandType
         * @param namespace
         * @param id
         * @param sqlSource
         */
        public Builder(Configuration configuration, SqlCommandType sqlCommandType, String namespace, String id, SqlSource sqlSource) {
            mappedStatement.configuration = configuration;
            mappedStatement.sqlCommandType = sqlCommandType;
            mappedStatement.namespace = namespace;
            mappedStatement.id = id;
            mappedStatement.statementId = namespace + "." + id;
            mappedStatement.sqlSource = sqlSource;
        }

        public Builder statementType(StatementType statementType) {
            mappedStatement.statementType = statementType;
            return this;
        }

        public Builder parameterTypeClass(Class<?> parameterTypeClass) {
            mappedStatement.parameterTypeClass = parameterTypeClass;
            return this;
        }

        public Builder resultTypeClass(Class<?> resultTypeClass) {
            mappedStatement.resultTypeClass = resultTypeClass;
            return this;
        }

        public Builder useGeneratedKeys(boolean useGeneratedKeys) {
            mappedStatement.useGeneratedKeys = useGeneratedKeys;
            return this;
        }

        public Builder keyProperty(String keyProperty) {
            mappedStatement.keyProperty = keyProperty;
            return this;
        }

        public Builder resultMapId(String resultMapId) {
            mappedStatement.resultMapId = resultMapId;
            return this;
        }

        public MappedStatement build() {
            assert mappedStatement.configuration != null;
            assert mappedStatement.statementId != null;
            assert mappedStatement.sqlSource != null;
            // 校验数据是否完整
            if(mappedStatement.sqlCommandType == SqlCommandType.SELECT) {
                assert (mappedStatement.resultTypeClass != null || mappedStatement.resultMapId != null);
            } else if(mappedStatement.sqlCommandType == SqlCommandType.INSERT) {
                if(mappedStatement.useGeneratedKeys) {
                    assert mappedStatement.keyProperty != null;
                }
            }
            return mappedStatement;
        }
    }

    public String getStatementId() {
        return statementId;
    }

    public Class<?> getParameterTypeClass() {
        return parameterTypeClass;
    }

    public Class<?> getResultTypeClass() {
        return resultTypeClass;
    }

    public StatementType getStatementType() {
        return statementType;
    }

    public SqlSource getSqlSource() {
        return sqlSource;
    }

    public boolean isUseGeneratedKeys() {
        return useGeneratedKeys;
    }

    public String getKeyProperty() {
        return keyProperty;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }
}
