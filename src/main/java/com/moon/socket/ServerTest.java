package com.moon.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 基于TCP协议的Socket通信，实现用户登录，服务端
 * Created by 12919 on 2017/12/20.
 */
public class ServerTest {
    public static void main(String[] args) throws IOException {
        // 1、创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
        // 1024-65535的某个端口
        ServerSocket serverSocket = new ServerSocket(8888);
        // 2、调用accept()方法开始监听，等待客户端的连接
        Socket socket = serverSocket.accept();
        // 3、获取输入流，并读取客户端信息
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String info = null;
        while((info=br.readLine()) != null) {
            System.out.println("我是服务器，客户端说：" + info);
        }
        // 关闭输入流
        socket.shutdownInput();
        // 4、获取输出流，响应客户端的请求
        OutputStream os = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(os);
        pw.write("欢迎您！");
        pw.flush();

        // 关闭资源
        pw.close();
        os.close();
        br.close();
        isr.close();
        is.close();
        socket.close();
        serverSocket.close();
    }
}
