package com.moon.socket;

import java.io.IOException;
import java.net.*;

/**
 * 客户端
 * Created by 12919 on 2017/12/20.
 */
public class UDPClient {
    public static void main(String[] args) throws IOException {
        // 1、定义服务器的地址、端口号、数据
        InetAddress address = InetAddress.getByName("localhost");
        int port = 8888;
        byte[] data = "用户名：admin;密码：123".getBytes();
        // 2、创建数据包，包含发送的数据信息
        DatagramPacket packet = new DatagramPacket(data, 0, data.length, address, port);
        // 3、创建DatagramSocket对象
        DatagramSocket socket = new DatagramSocket();
        // 4、箱服务发送数据
        socket.send(packet);

        // 接收服务器端响应数据
        // ========================================================================
        // 1、创建数据包，用于接收服务器端响应数据
        byte[] data2 = new byte[1024];
        DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
        // 2、接收服务器端的响应的数据
        socket.receive(packet2);
        String reply = new String(data2, 0, packet2.getLength());
        System.out.println("我是客户端，服务器说：" + reply);
        // 3、关闭资源
        socket.close();
    }
}
