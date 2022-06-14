package com.moon.concurrency.blockimpl;

/**
 * 使用条件对列实现的有界缓存
 * @author yujiangtao
 * @date 2019/3/21 16:01
 */
public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {

    // 条件谓词：not-full (!isFull())
    // 条件谓词：not-empty (!isEmpty())

    public BoundedBuffer(int size) {
        super(size);
    }

    // 阻塞并直到：not-full
    public synchronized void put(V v) throws InterruptedException {
        while(isFull()) {
            wait();
        }
        doPut(v);
        notifyAll();
    }

    // 阻塞并直到：not-empty
    public synchronized V take() throws InterruptedException {
        while(isEmpty()) {
            wait();
        }
        V v = doTake();
        notifyAll();
        return v;
    }
}
