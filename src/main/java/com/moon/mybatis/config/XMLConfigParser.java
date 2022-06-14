package com.moon.mybatis.config;

import com.moon.mybatis.transaction.JdbcTransactionFactory;
import com.moon.mybatis.transaction.TransactionFactory;
import com.moon.mybatis.util.DocumentUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * 用来解析全局配置文件
 *
 * @author yujiangtao
 * @date 2020/8/3 下午12:24
 */
public class XMLConfigParser {

    private Configuration configuration;

    public XMLConfigParser() {
        configuration = new Configuration();
    }

    /**
     * 读取 <configuration></configuration>
     * @param rootElement <configuration>
     * @return
     */
    public Configuration parse(Element rootElement) {
        parseEnvironments(rootElement.element("environments"));
        parseSettings(rootElement.element("settings"));
        parseMappers(rootElement.element("mappers"));
        return configuration;
    }

    /**
     * 读取 <settings></settings>
     * @param settingsEle
     */
    private void parseSettings(Element settingsEle) {
        List<Element> setting = settingsEle.elements("setting");
        Properties properties = new Properties();
        for (Element element : setting) {
            properties.setProperty(element.attributeValue("name"),
                    element.attributeValue("value"));
        }
        configuration.setSettings(properties);
    }

    /**
     * 解析 mappers 子标签，最终该标签会去解析每个映射文件
     * @param mappersEle
     */
    private void parseMappers(Element mappersEle) {
        List<Element> mappers = mappersEle.elements("mapper");
        for (Element mapper : mappers) {
            parseMapper(mapper);
        }
    }

    private void parseMapper(Element mapper) {
        // 获取映射文件路径
        String resource = mapper.attributeValue("resource");
        // 获取指定路径的 IO 流
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
        configuration.addLoadedResources(resource);
        // 获取映射文件对应的 Document 对象
        Document document = DocumentUtils.readDoucment(inputStream);
        // 按照 mapper 标签语义去解析 Document
        XMLMapperParser mapperParser = new XMLMapperParser(configuration);
        mapperParser.parse(document.getRootElement());
    }

    /**
     *
     * @param element <environments>
     */
    private void parseEnvironments(Element element) {
        String defaultEnvId = element.attributeValue("default");
        if(defaultEnvId == null || "".equals(defaultEnvId)) {
            return;
        }
        List<Element> environments = element.elements("environment");
        for (Element environment : environments) {
            String envId = environment.attributeValue("id");
            // 判断 defaultEnvId 和 envId 是否一致，一致再继续解析
            if(defaultEnvId.equals(envId)) {
                parseEnvironment(environment, defaultEnvId);
            }
        }
    }

    /**
     *
     * @param envEle <environment>
     * @param envId
     */
    private void parseEnvironment(Element envEle, String envId) {
        Element dataSourceEnv = envEle.element("dataSource");

        // 读取 transactionManager 信息
        TransactionFactory txFactory = parseTransactionManager(envEle.element("transactionManager"));

        Environment.Builder envBuilder = new Environment.Builder(envId).transactionFactory(txFactory);

        String type = dataSourceEnv.attributeValue("type");
        type = type == null || "".equals(type) ? "DBCP" : type;
        if("DBCP".equals(type)) {
            Properties properties = parseProperty(dataSourceEnv);
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(properties.getProperty("driver"));
            dataSource.setUrl(properties.getProperty("url"));
            dataSource.setUsername(properties.getProperty("username"));
            dataSource.setPassword(properties.getProperty("password"));

            // 将解析出来的 DataSource 封装到 Configuration
            Environment environment = envBuilder.dataSource(dataSource).build();
            configuration.setEnvironment(environment);
        }
    }

    private TransactionFactory parseTransactionManager(Element txManagerEle) {
        if(txManagerEle == null) {
            throw new ConfigException("<environment> declaration requires a <transactionManager>.");
        }
        String type = txManagerEle.attributeValue("type");
        TransactionFactory transactionFactory = null;
        switch (type.trim().toUpperCase()) {
            case "JDBC":
                transactionFactory = new JdbcTransactionFactory();
                break;
            default:
                ;
        }

        return transactionFactory;
    }

    /**
     * 读取数据源的配置信息
     * @param element
     * @return
     */
    private Properties parseProperty(Element element) {
        List<Element> props = element.elements("property");
        Properties properties = new Properties();
        for (Element property : props) {
            String name = property.attributeValue("name");
            String value = property.attributeValue("value");
            properties.put(name, value);
        }
        return properties;
    }

}
