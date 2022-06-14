package com.moon.mybatis.sqlsession;

import com.moon.mybatis.config.Configuration;
import com.moon.mybatis.config.XMLConfigParser;
import com.moon.mybatis.util.DocumentUtils;
import org.dom4j.Document;

import java.io.InputStream;
import java.io.Reader;

/**
 * 使用构建者模式对 SqlSessionFactory 进行创建
 *
 * @author yujiangtao
 * @date 2020/8/6 下午3:57
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream inputStream) {
        // 获取 Configuration 对象
        Document document = DocumentUtils.readDoucment(inputStream);
        XMLConfigParser configParser = new XMLConfigParser();
        Configuration configuration = configParser.parse(document.getRootElement());
        return build(configuration);
    }

    public SqlSessionFactory build(Reader reader) {
        return null;
    }

    private SqlSessionFactory build(Configuration configuration) {
        return new DefaultSqlSessionFactory(configuration);
    }
}
