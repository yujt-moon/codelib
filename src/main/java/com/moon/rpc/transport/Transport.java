package com.moon.rpc.transport;

import java.util.concurrent.CompletableFuture;

/**
 * @author yujiangtao
 * @date 2021/1/30 下午2:29
 */
public interface Transport {

    /**
     * 发送请求命令
     * @param request 请求命令
     * @return 返回一个 Future
     */
    CompletableFuture<Command> send(Command request);
}
