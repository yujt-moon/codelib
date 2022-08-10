package com.moon.multithread.orderprint;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yujiangtaoa
 * @date 2022/7/20 下午4:28
 */
public class UsingReentrantLock {

    public static void main(String[] args) {
        char[] s1 = "123456789".toCharArray();
        char[] s2 = "ABCDEFGHI".toCharArray();

        Lock lock = new ReentrantLock(); // 替代 synchronized
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock.lock();
            try {
                for (char c : s1) {
                    System.out.print(c);
                    condition2.signal();
                    condition1.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                for (char c : s2) {
                    System.out.print(c);
                    latch.countDown();
                    condition1.signal();
                    condition2.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}
