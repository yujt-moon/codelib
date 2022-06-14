package com.moon.concurrency.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁（SpinLock）：
 *      多个线程，当一个线程尝试获取锁的时候，如果锁被占用，
 *      就在当前线程循环检查锁是否被释放，这个时候线程并没有进入休眠或者挂起。
 *
 * <a href="https://www.jianshu.com/p/1b1b44e84394?utm_source=oschina-app">
 *     自旋锁&CLH锁&MCS锁学习记录
 * </a>
 *
 * @author yujiangtao
 * @date 2020/10/12 下午2:47
 */
public class SpinLock {

    // AtomicReference, CAS, compareAndSet 保证了操作的原子性
    private AtomicReference<Thread> owner = new AtomicReference<>();

    public void lock() {
        Thread thread = Thread.currentThread();

        // 如果锁未被占用，则设置当前线程为锁的拥有者，设置成功返回true，否则返回false
        // null为期望值，currentThread为要设置的值，如果当前内存值和期望值null相等，替换为currentThread
        while(!owner.compareAndSet(null, thread)) {}
        System.out.println(thread.getName() + " get the lock!");
    }

    public void unlock() {
        Thread thread = Thread.currentThread();

        // 只有锁的拥有者才能释放锁，只有上锁的线程获取到的currentThread，才能和内存中的currentThread相等
        if(owner.compareAndSet(thread, null)) {
            System.out.println(thread.getName() + " release the lock!");
        }
    }
}
