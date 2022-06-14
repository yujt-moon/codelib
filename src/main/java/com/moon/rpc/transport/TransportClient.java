package com.moon.rpc.transport;

import java.io.Closeable;
import java.io.IOException;
import java.net.SocketAddress;
import java.util.concurrent.TimeoutException;

/**
 * @author yujiangtao
 * @date 2021/2/2 下午11:14
 */
public interface TransportClient extends Closeable {

    Transport createTransport(SocketAddress address, long connectionTimeout) throws InterruptedException, TimeoutException;

    @Override
    void close() throws IOException;
}
