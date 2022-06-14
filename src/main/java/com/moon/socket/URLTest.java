package com.moon.socket;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 12919 on 2017/12/20.
 */
public class URLTest {
    public static void main(String[] args) throws MalformedURLException {
        // 创建一个URL的实例
        URL baidu = new URL("http://www.baidu.com");
        // ?表示参数，#表示锚点
        URL url = new URL(baidu, "/index.html?username=tom#test");
        // 获取协议
        String protocol = url.getProtocol();
        System.out.println(protocol);
        // 获取主机
        String host = url.getHost();
        System.out.println(host);
        // 如果没有指定端口号，根据协议不同使用默认端口号。此时getPort()方法的返回值为-1
        int port = url.getPort();
        System.out.println(port);
        // 获取文件路径
        String path = url.getPath();
        System.out.println(path);
        // 文件名，包括文件路径+参数
        String file = url.getFile();
        System.out.println(file);
        // 相对路径，就是锚点，即#号后面的内容
        String ref = url.getRef();
        System.out.println(ref);
        // 查询字符串，即参数
        String query = url.getQuery();
        System.out.println(query);
    }
}
