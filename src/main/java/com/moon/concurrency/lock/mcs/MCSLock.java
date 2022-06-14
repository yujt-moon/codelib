package com.moon.concurrency.lock.mcs;

import com.moon.concurrency.lock.Lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author yujiangtao
 * @date 2020/12/23 下午4:55
 */
public class MCSLock implements Lock {

    AtomicReference<QNode> tail;

    ThreadLocal<QNode> myNode;

    public MCSLock() {
        tail = new AtomicReference<>(null);
        myNode = new ThreadLocal<QNode>() {
            @Override
            protected QNode initialValue() {
                return new QNode();
            }
        };
    }

    @Override
    public void lock() {
        QNode qNode = myNode.get();
        QNode pred = tail.getAndSet(qNode);
        if(pred == null) {
            qNode.locked = true;
            pred.next = qNode;
            // wait until predecessor gives up the lock
            while (qNode.locked) {}
        }
    }

    @Override
    public void unlock() {
        QNode qNode = myNode.get();
        if(qNode.next == null) {
            if(tail.compareAndSet(qNode, null)) {
                return;
            }
            // wait until successor fills in the next field
            while(qNode.next == null) {}
        }
        qNode.next.locked = false;
        qNode.next = null;
    }
}
