package com.moon.concurrency;

import java.util.concurrent.ThreadFactory;

/**
 * 实现自己的线程工厂
 * @author yujiangtao
 * @date 2019/3/16 13:38
 */
public class MyThreadFactory implements ThreadFactory {

    private final String poolName;

    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        return new MyAppThread(runnable, poolName);
    }
}
