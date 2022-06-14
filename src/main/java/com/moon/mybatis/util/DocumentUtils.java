package com.moon.mybatis.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * dom4j 文档读取工具
 *
 * @author yujiangtao
 * @date 2020/8/3 下午12:55
 */
public class DocumentUtils {

    /**
     * 读取文档信息
     * @param is
     * @return
     */
    public static Document readDoucment(InputStream is) {
        Document document = null;
        SAXReader reader = new SAXReader();
        try {
            document = reader.read(is);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }
}
