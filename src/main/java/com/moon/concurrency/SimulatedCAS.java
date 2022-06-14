package com.moon.concurrency;

/**
 * 模拟CAS操作
 * @author yujiangtao
 * @date 2019/3/22 15:11
 */
public class SimulatedCAS {

    private int value;

    public synchronized int get() {
        return value;
    }

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if(oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }

    public synchronized boolean compareAndSet(int expectedValue, int newValue) {
        return (expectedValue == compareAndSwap(expectedValue, newValue));
    }
}
