package com.moon.concurrency.lock.clh;

import com.moon.concurrency.lock.Lock;

import java.util.concurrent.TimeUnit;

/**
 * @author yujiangtao
 * @date 2020/12/22 下午3:08
 */
public class CLHLockDemo {

    public static void main(String[] args) {
        final Kfc kfc = new Kfc();
        for (int i = 0; i < 10; i++) {
            new Thread("cook" + i) {
                @Override
                public void run() {
                    kfc.cook();
                }
            }.start();

            new Thread("eat" + i) {
                @Override
                public void run() {
                    kfc.eat();
                }
            }.start();
        }
    }
}

class Kfc {

    private final Lock lock = new CLHLock();

    private int i = 0;

    public void eat() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + ": " + --i);
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void cook() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + ": " + ++i);
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
