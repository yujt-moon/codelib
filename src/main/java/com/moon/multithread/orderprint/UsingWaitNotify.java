package com.moon.multithread.orderprint;

import java.util.concurrent.CountDownLatch;

/**
 * https://www.bilibili.com/video/BV1ut4y1b7xx?p=7&vd_source=a90ad52da3413fbdf07d4bf807ffc6d1
 *
 * @author yujiangtaoa
 * @date 2022/7/20 下午3:40
 */
public class UsingWaitNotify {

    public static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {
        char[] s1 = "123456789".toCharArray();
        char[] s2 = "ABCDEFGHI".toCharArray();

        final Object o = new Object();

        new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o) {
                for (char c : s1) {
                    System.out.print(c);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify(); // 必须否则无法停止线程
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (o) {
                for (char c : s2) {
                    System.out.print(c);
                    latch.countDown();
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify(); // 必须，否则无法停止线程
            }
        }, "t2").start();
    }
}
