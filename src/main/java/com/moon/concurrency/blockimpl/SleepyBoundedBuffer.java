package com.moon.concurrency.blockimpl;

/**
 * 使用简单的阻塞实现的有界缓存
 * @author yujiangtao
 * @date 2019/3/21 15:44
 */
public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

    private final static int SLEEP_GRANULARITY = 2000;

    public SleepyBoundedBuffer(int size) {
        super(size);
    }

    public void put(V v) throws InterruptedException {
        while(true) {
            synchronized (this) {
                if(!isFull()) {
                    doPut(v);
                    return;
                }
            }
            Thread.sleep(SLEEP_GRANULARITY);
        }
    }

    public V take() throws InterruptedException {
        while(true) {
            synchronized (this) {
                if(!isEmpty()) {
                    return doTake();
                }
            }
            Thread.sleep(SLEEP_GRANULARITY);
        }
    }
}
