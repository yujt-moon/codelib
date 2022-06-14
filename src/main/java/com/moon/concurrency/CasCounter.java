package com.moon.concurrency;

/**
 * 基于CAS实现的非阻塞计数器
 * @author yujiangtao
 * @date 2019/3/22 15:35
 */
public class CasCounter {

    private SimulatedCAS value;

    public int getValue() {
        return value.get();
    }

    public int increment() {
        int v;
        do {
            v = value.get();
        } while (v != value.compareAndSwap(v, v + 1));
        return v + 1;
    }
}
