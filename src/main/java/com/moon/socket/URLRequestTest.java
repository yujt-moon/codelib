package com.moon.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 12919 on 2017/12/20.
 */
public class URLRequestTest {
    public static void main(String[] args) throws IOException {
        // 使用URL读取网页内容
        // 创建一个URL实例
        URL url = new URL("http://www.baidu.com");
        // 通过openStream()方法获取资源的字节输入流
        InputStream is = url.openStream();
        // 将字节输入流转换为字符输入流，如果不指定卞，码，中文可能出现乱码
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
        // 为字符输入流添加缓冲，提高读取效率
        BufferedReader br = new BufferedReader(isr);
        // 读取数据
        String data = br.readLine();
        while(data != null) {
            // 输出数据
            System.out.println(data);
            data = br.readLine();
        }
        br.close();
        isr.close();
        is.close();
    }
}
