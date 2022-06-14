package com.moon.io;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author yujiangtao
 * @date 2020/11/4 下午10:54
 */
public class NIOServer extends Thread {

    @Override
    public void run() {
        try (Selector selector = Selector.open();
        ServerSocketChannel serverSocket = ServerSocketChannel.open();) { // 创建 Selector 和 Channel
            serverSocket.socket().bind(new InetSocketAddress(InetAddress.getLocalHost(), 8888));
            serverSocket.configureBlocking(false);
            // 注册到 Selector，并说明关注点
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            ByteBuffer buffer = ByteBuffer.allocate(64);
            while(true) {
                selector.select(); // 阻塞等待就绪的 Channel，这是关键点之一
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectionKeys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    // 生产系统中一般会额外进行就绪状态检查
                    if(key.isAcceptable()) {
                        handleAccept(key);
                    } else if (key.isReadable()){
                        handleRead(key);
                    } else if (key.isConnectable()) {
                        handleConnect();
                    } else if (key.isWritable()) {

                    }
                    iter.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleAccept(SelectionKey key) {
        try (SocketChannel client = ((ServerSocketChannel) key.channel()).accept();) {
            client.write(Charset.defaultCharset().encode("Hello world!\n"));
            System.out.println("accepted ...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleRead(SelectionKey selectionKey) throws IOException {
        SocketChannel client = (SocketChannel) selectionKey.channel();
        ByteBuffer buffer = ByteBuffer.allocate(64);
        int read = client.read(buffer);
        if (read != -1) {
            buffer.flip();
            String receiveData = StandardCharsets.UTF_8.decode(buffer).toString();
            System.out.println(receiveData);
            buffer.clear();
        }
    }

    private void handleConnect() {
        System.out.println("connecting ...");
    }

    public static void main(String[] args) throws IOException {
        NIOServer server = new NIOServer();
        server.start();
    }
}
