package com.moon.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过显示锁（Lock）和条件队列（Condition）模拟实现信号量
 * 并非java.util.concurrent.Semaphore的真实实现方式
 * @author yujiangtao
 * @date 2019/3/22 9:45
 */
public class SemaphoreOnLock {

    private final Lock lock = new ReentrantLock();

    // 条件谓词：permitsAvailiable (permits > 0)
    private final Condition permitsAvailiable = lock.newCondition();

    private int permits;

    SemaphoreOnLock(int initialPermits) {
         lock.lock();
         try {
            permits = initialPermits;
         } finally {
             lock.unlock();
         }
    }

    // 阻塞并直到：permitsAvailiable
    public void acquire() throws InterruptedException {
        lock.lock();
        try {
            while(permits <= 0) {
                permitsAvailiable.await();
            }
            --permits;
        } finally {
            lock.unlock();
        }
    }

    public void release() {
        lock.lock();
        try {
            ++permits;
            permitsAvailiable.signal();
        } finally {
            lock.unlock();
        }
    }
}
