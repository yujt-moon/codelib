package com.moon.rpc.transport;

import java.util.concurrent.CompletableFuture;

/**
 * @author yujiangtao
 * @date 2021/1/30 下午3:09
 */
public class ResponseFuture {

    private final int requestId;

    private final CompletableFuture<Command> future;

    private final long timestamp;

    public ResponseFuture(int requestId, CompletableFuture<Command> future) {
        this.requestId = requestId;
        this.future = future;
        this.timestamp = System.nanoTime();
    }

    public int getRequestId() {
        return requestId;
    }

    public CompletableFuture<Command> getFuture() {
        return future;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
