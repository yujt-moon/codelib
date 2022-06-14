package com.moon.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yujiangtao
 * @since 1.0
 */
public class ThreadTest {
    //public volatile int inc = 0;

    /**
     * 1、inc的值总是小鱼10000
     */
    /*public void increase() {
        inc++;
    }*/

    /**
     * 使用synchronized关键字加锁
     */
    /*public synchronized void increase() {
        inc++;
    }*/

    /**
     * 使用Lock
     */
    /*Lock lock = new ReentrantLock();
    public void increase() {
        lock.lock();
        try {
            inc++;
        } finally {
            lock.unlock();
        }
    }*/

    public AtomicInteger inc = new AtomicInteger();

    public void increase() {
        inc.getAndIncrement();
    }

    public static void main(String[] args) {
        final ThreadTest test = new ThreadTest();
        for(int i = 0; i < 10; i++){
            new Thread(){
                @Override
                public void run() {
                    for(int j = 0; j < 1000; j++) {
                        test.increase();
                    }
                }
            }.start();
        }

        while(Thread.activeCount()>2) { //保证前面的线程都执行完
            Thread.yield();
        }
        System.out.println(test.inc);
    }
}