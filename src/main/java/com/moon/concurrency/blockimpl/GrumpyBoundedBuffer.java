package com.moon.concurrency.blockimpl;

import com.moon.concurrency.BufferFullException;

/**
 * 不好的实现方式
 * （客户端需要自己实现对容易的空和满的处理，要自己实现等待）
 * @author yujiangtao
 * @date 2019/3/21 15:27
 */
public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

    public GrumpyBoundedBuffer(int size) {
        super(size);
    }

    // 不合理的抛出异常
    public synchronized void put(V v) throws BufferFullException {
        if(isFull()) {
            throw new BufferFullException();
        }
        doPut(v);
    }

    // 不合理的抛出异常
    public synchronized V take() throws BufferFullException {
        if(isEmpty()) {
            throw new BufferFullException();
        }
        return doTake();
    }
}
