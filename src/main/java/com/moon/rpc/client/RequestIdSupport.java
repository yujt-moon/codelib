package com.moon.rpc.client;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yujiangtao
 * @date 2021/1/30 下午4:09
 */
public class RequestIdSupport {

    private final static AtomicInteger nextRequestId = new AtomicInteger(0);

    public static int next() {
        return nextRequestId.getAndIncrement();
    }
}
