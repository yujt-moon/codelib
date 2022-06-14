package com.moon.mybatis;

import com.moon.mybatis.config.Configuration;
import com.moon.mybatis.config.XMLConfigParser;
import com.moon.mybatis.transaction.JdbcTransactionFactory;
import com.moon.mybatis.util.DocumentUtils;
import org.dom4j.Document;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author yujiangtao
 * @date 2020/9/5 下午2:02
 */
public class ReadConfigTest {

    @Test
    public void testInitConfiguration() throws Exception {
        // 指定全局配置文件的位置
        String resource = "mybatis-config.xml";
        // 获取指定路径的 IO 流
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
        // 获取 Document 对象
        Document document = DocumentUtils.readDoucment(inputStream);
        // 解析 Document 获取 Configuration 对象
        XMLConfigParser configParser = new XMLConfigParser();

        // <configuration>
        Configuration configuration = configParser.parse(document.getRootElement());

        Assert.assertNotNull(configuration);

        Assert.assertEquals("development", configuration.getEnvironment().getId());
        Assert.assertTrue(configuration.getEnvironment().getTransactionFactory() instanceof JdbcTransactionFactory);
        Assert.assertEquals("STDOUT_LOGGING", configuration.getSettings().getProperty("logImpl"));
        Assert.assertNotNull(configuration.getLoadedResources().contains("mapper/bookDao.xml"));
    }
}
