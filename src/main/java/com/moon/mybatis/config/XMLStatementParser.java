package com.moon.mybatis.config;

import com.moon.mybatis.mapping.SqlCommandType;
import com.moon.mybatis.mapping.StatementType;
import com.moon.mybatis.sqlsource.SqlSource;
import com.moon.util.StringUtils;
import org.dom4j.Element;

import java.util.Locale;


/**
 * @author yujiangtao
 * @date 2020/8/3 下午8:21
 */
public class XMLStatementParser {

    private Configuration configuration;

    public XMLStatementParser(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parseStatement(Element tagElem, String namespace, SqlCommandType sqlCommandType) {
        // 一个 MappedStatement 对象，就对应一个 select|insert|update|delete 标签
        String id = tagElem.attributeValue("id");
        String parameterType = tagElem.attributeValue("parameterType");
        String resultType = tagElem.attributeValue("resultType");
        String statementTypeStr = tagElem.attributeValue("statementType") == null ? "" :
                tagElem.attributeValue("statementType");
        boolean useGeneratedKeys = tagElem.attributeValue("useGeneratedKeys") == null ? false :
                Boolean.valueOf(tagElem.attributeValue("useGeneratedKeys"));
        String keyProperty = tagElem.attributeValue("keyProperty");
        String resultMapId = tagElem.attributeValue("resultMap");


        Class<?> parameterTypeClass = resolveClass(parameterType);
        Class<?> resultTypeClass = resolveClass(resultType);
        StatementType statementType = StringUtils.isBlank(statementTypeStr) ? StatementType.STATEMENT :
                StatementType.valueOf(statementTypeStr.trim().toUpperCase(Locale.US));

        // SqlSource 就是封装了SQL语句
        // 此时封装的SQL语句是没有进行处理的，什么时候处理呢？
        // 处理时机，就是在 SqlSession 执行的时候
        // SELECT * FROM user WHERE id = #{id}
        // String sqlText = selectEle.getTextTrim();
        // SqlSource sqlSource = new SqlSource(sqlText);
        SqlSource sqlSource = createSqlSource(tagElem);

        String statementId = namespace + "." + id;

        // 此处使用构造方法或者 set 方法赋值的话，感觉不舒服
        // 使用构建者模式
        MappedStatement mappedStatement = new MappedStatement
                .Builder(configuration, sqlCommandType, namespace, id, sqlSource)
                .statementType(statementType)
                .parameterTypeClass(parameterTypeClass)
                .resultTypeClass(resultTypeClass)
                .useGeneratedKeys(useGeneratedKeys)
                .keyProperty(keyProperty)
                .resultMapId(resultMapId)
                .build();
        configuration.setMappedStatement(statementId, mappedStatement);
    }

    /**
     * 创建 SqlSource 其实就是对 select 等 CRUD 标签中的 sql 脚本进行处理
     *
     * @param element
     * @return
     */
    private SqlSource createSqlSource(Element element) {
        XMLScriptParser scriptParser = new XMLScriptParser(configuration);
        SqlSource sqlSource = scriptParser.parseScriptNode(element);
        return sqlSource;
    }

    /**
     * 将 class 的全限定名解析成 class 对象
     * @param name
     * @return
     */
    private Class<?> resolveClass(String name) {
        if(StringUtils.isBlank(name)) {
            return null;
        }
        Class<?> clazz = null;
        try {
            clazz = Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }
}
