package com.moon.io;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * AcceptHandler 类实现了 CompletionHandler 接口的 completed 方法。它还有两个模板参数，
 * 第一个是异步通道，第二个就是 Nio2Server 本身
 *
 * @author yujiangtaoa
 * @date 2022/5/5 上午11:27
 */
public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, Nio2Server> {

    @Override
    public void completed(AsynchronousSocketChannel asc, Nio2Server attachment) {
        // 调用 accept 方法继续接受其他客户端的请求

    }

    @Override
    public void failed(Throwable exc, Nio2Server attachment) {

    }
}
