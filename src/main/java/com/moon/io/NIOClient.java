package com.moon.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 *
 * https://ifeve.com/overview/
 *
 * @author yujiangtao
 * @date 2020/11/5 上午10:41
 */
public class NIOClient extends Thread {

    private boolean connected = false;

    private ByteBuffer buffer = ByteBuffer.allocate(64);

    private ByteBuffer writeBuffer = ByteBuffer.allocate(64);

    @Override
    public void run() {
        try (SocketChannel socket = SocketChannel.open();
             Selector selector = Selector.open();) {
            socket.configureBlocking(false);
            socket.connect(new InetSocketAddress(InetAddress.getLocalHost(), 8888));
            socket.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);

            Thread keyboard = new Thread(() -> {
                handleInput(socket);
            });

            while (true) {
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isConnectable() && socket.finishConnect()) {
                        connected = true;
                        keyboard.start();
                    } else if (key.isReadable()) {
                        handleRead(key);
                        System.out.println("> ");
                    } else if (key.isWritable()) {

                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NIOClient client = new NIOClient();
        client.start();
    }

    private void handleRead(SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        int bytesRead = client.read(buffer);
        while (bytesRead != -1) {
            buffer.flip();
            System.out.print(StandardCharsets.UTF_8.decode(buffer));
            buffer.clear();
        }
    }

    private void handleInput(SocketChannel client) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String input = "";
            while ((input = bufferedReader.readLine()) != null) {
                System.out.print(input);
                writeBuffer.put(input.getBytes(StandardCharsets.UTF_8));
                client.write(writeBuffer);
                writeBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
