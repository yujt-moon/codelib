package com.moon.rpc.transport;

/**
 * @author yujiangtao
 * @date 2021/2/2 下午11:09
 */
public interface TransportServer {

    void start(RequestHandlerRegistry requestHandlerRegistry, int port) throws Exception;

    void stop();
}
