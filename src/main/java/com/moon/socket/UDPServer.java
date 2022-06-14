package com.moon.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * 服务器端，实现基于UDP法人用户登录
 * Created by 12919 on 2017/12/20.
 */
public class UDPServer {
    public static void main(String[] args) throws IOException {
        // 1、创建服务器端DatagramSocket,指定端口
        DatagramSocket socket = new DatagramSocket(8888);
        // 2、创建数据包，用于接收客户端发送的数据
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        // 3、接收客户端发送的数据
        // 此方法在接收数据包之前会一直阻塞
        socket.receive(packet);
        // 4、读取数据
        String info = new String(data, 0, data.length);
        System.out.println("我是服务器，客户端告诉我" + info);


        // =====================================================
        // 向客户端响应数据
        // 1、定义客户端的地址、端口号、数据
        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        byte[] data2 = "欢迎您！".getBytes();
        // 2、创建数据包，包含响应的数据信息
        DatagramPacket packet2 = new DatagramPacket(data2, data2.length, address, port);
        // 3、响应客户端
        socket.send(packet2);
        // 4、关闭资源
        socket.close();
    }
}
