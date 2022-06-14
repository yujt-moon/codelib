package com.moon.concurrency;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 有界阻塞容器（使用信号量semaphore）
 * @author yujiangtao
 * @date 2019/2/17 13:55
 */
public class BoundedHashSet<T> {
    /**
     * 使用组合而非继承
     */
    private final Set<T> set;
    private final Semaphore sem;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<T>());
        sem = new Semaphore(bound);
    }

    /**
     * 添加新元素
     * （如果已经添加满则阻塞，等待有新的空位出现）
     * @param o
     * @return
     * @throws InterruptedException
     */
    public boolean add(T o) throws InterruptedException {
        sem.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = set.add(o);
            return wasAdded;
        } finally {
            if(!wasAdded) {
                sem.release();
            }
        }
    }

    /**
     * 移除元素
     * （成功则释放信号量）
     * @param o
     * @return
     */
    public boolean remove(Object o) {
        boolean wasRemoved = set.remove(o);
        if(wasRemoved) {
            sem.release();
        }
        return wasRemoved;
    }
}
