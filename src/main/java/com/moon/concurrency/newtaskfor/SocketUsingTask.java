package com.moon.concurrency.newtaskfor;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * @author yujiangtao
 * @date 2019/3/10 14:59
 */
public abstract class SocketUsingTask<T> implements CancellableTask<T> {
    private Socket socket;

    protected synchronized void setSocket(Socket s) {
        this.socket = s;
    }

    public synchronized void cancel() {
        try {
            if(socket != null) {
                socket.close();
            }
        } catch (IOException e) {

        }
    }

    public RunnableFuture<T> newTask() {
        return new FutureTask<T>(this) {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                try {
                    SocketUsingTask.this.cancel();
                } finally {
                    return super.cancel(mayInterruptIfRunning);
                }
            }
        };
    }
}
