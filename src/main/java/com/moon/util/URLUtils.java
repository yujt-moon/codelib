package com.moon.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * URL工具类
 * @author yujiangtao
 * @since 1.0
 */
public class URLUtils {

    /**
     * 对url进行编码
     * @param urlStr 需要编码的url
     * @param encoding 指定编码
     * @return
     */
    public static String encodeUrl(String urlStr, String encoding) {
        String encodeUrl = null;
        try {
            encodeUrl = URLEncoder.encode(urlStr, encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeUrl;
    }

    /**
     * 对url进行编码（默认使用UTF-8编码）
     * @param urlStr 需要编码的url
     * @return
     */
    public static String encodeUrl(String urlStr) {
        return encodeUrl(urlStr, "UTF-8");
    }

    /**
     * 对编码过的url进行解码
     * @param urlStr 需要解码的url
     * @param encoding 指定的编码
     * @return
     */
    public static String decodeUrl(String urlStr, String encoding) {
        String decodeUrl = null;
        try {
            decodeUrl = URLDecoder.decode(urlStr, encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decodeUrl;
    }

    /**
     * 对编码过的url进行解码
     * @param urlStr 需要解码的url
     * @return
     */
    public static String decodeUrl(String urlStr) {
        return decodeUrl(urlStr, "UTF-8");
    }
}
