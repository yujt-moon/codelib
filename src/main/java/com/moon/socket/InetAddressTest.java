package com.moon.socket;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by 12919 on 2017/12/19.
 */
public class InetAddressTest {

    public static void main(String[] args) throws IOException {
        // 获取本机的InetAddress实例
        InetAddress address = InetAddress.getLocalHost();
        System.out.println(address);
        // 获取计算机名
        String hostName = address.getHostName();
        System.out.println(hostName);
        // 获取IP地址
        String hostAddress = address.getHostAddress();
        System.out.println(hostAddress);
        // 获取字节数组形式的IP地址,以点分隔的四部分
        byte[] address2 = address.getAddress();
        System.out.println(new String(address2, "UTF-8"));

        // 获取其他主机的InetAddress实例
        InetAddress addressName = InetAddress.getByName("www.baidu.com");
        System.out.println(addressName);
        InetAddress byName = InetAddress.getByName("180.97.33.108");
        System.out.println(byName);
    }
}
