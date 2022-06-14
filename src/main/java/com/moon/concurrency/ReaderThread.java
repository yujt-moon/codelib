package com.moon.concurrency;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author yujiangtao
 * @date 2019/3/10 14:41
 */
public class ReaderThread extends Thread {
    private final Socket socket;
    private final InputStream in;

    public static final int BUFSZ = 1024;

    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    @Override
    public void interrupt() {
        try {
            socket.close();
        } catch (IOException ignored) {
            /* 允许线程退出 */
        } finally {
            super.interrupt();
        }
    }

    @Override
    public void run() {
        try {
            byte[] buf = new byte[BUFSZ];
            while(true) {
                int count = in.read(buf);
                if(count < 0) {
                    break;
                } else if(count > 0) {
                    processBuffer(buf, count);
                }
            }
        } catch (IOException e) {

        }
    }

    private void processBuffer(byte[] buf, int count) {

    }
}
