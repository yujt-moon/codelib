package com.moon.mybatis.config;

import com.moon.mybatis.mapping.SqlCommandType;
import com.moon.mybatis.util.ClassUtils;
import org.dom4j.Element;

import java.util.List;

/**
 * @author yujiangtao
 * @date 2020/8/3 下午3:11
 */
public class XMLMapperParser {

    private Configuration configuration;

    public XMLMapperParser(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(Element rootElement) {
        String namespace = rootElement.attributeValue("namespace");
        // mapper 标签下会包含一些sql片段标签， resultMap 标签等，这些标签直接解析处理，而有些 statement 相关的标签单独处理
        // 此处可以使用 XPath 语法来进行通配
        List<Element> selects = rootElement.elements("select");
        for (Element select : selects) {
            // select|update|delete|insert
            XMLStatementParser statementParser = new XMLStatementParser(configuration);
            statementParser.parseStatement(select, namespace, SqlCommandType.SELECT);
        }

        List<Element> inserts = rootElement.elements("insert");
        for (Element insert : inserts) {
            XMLStatementParser statementParser = new XMLStatementParser(configuration);
            statementParser.parseStatement(insert, namespace, SqlCommandType.INSERT);
        }

        List<Element> updates = rootElement.elements("update");
        for (Element update : updates) {
            XMLStatementParser statementParser = new XMLStatementParser(configuration);
            statementParser.parseStatement(update, namespace, SqlCommandType.UPDATE);
        }

        List<Element> deletes = rootElement.elements("delete");
        for (Element delete : deletes) {
            XMLStatementParser statementParser = new XMLStatementParser(configuration);
            statementParser.parseStatement(delete, namespace, SqlCommandType.DELETE);
        }
    }

    /**
     * 读取 <resultMap>
     * @param rootEle
     */
    private void parseResultMaps(Element rootEle) {
        List<Element> resultMaps = rootEle.elements("resultMap");
        for (Element resultMap : resultMaps) {
            parseResultMap(resultMap);
        }
    }

    private void parseResultMap(Element resMapEle) {
        // 读取 id 属性
        String id = resMapEle.attributeValue("id");
        // 读取 type 属性
        String type = resMapEle.attributeValue("type");
        // 读取 resultType 属性
        Class<?> resultType = ClassUtils.resolveClass(resMapEle.attributeValue("resultType"));
        parseResultMapChildren(resMapEle);
    }

    private void parseResultMapChildren(Element resMapEle) {
        for (Element element : (List<Element>) resMapEle.elements()) {
            String tagName = element.getName();
            if("constructor".equals(tagName)) {
                // 处理 <constructor> 节点
                processConstructorElement();
            }
        }
    }

    private void processConstructorElement() {

    }
}
