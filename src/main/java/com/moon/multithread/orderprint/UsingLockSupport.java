package com.moon.multithread.orderprint;

import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

/**
 * @author yujiangtaoa
 * @date 2022/7/20 下午3:28
 */
public class UsingLockSupport {

    Thread t1, t2 = null;

    @Test
    public void print() {

        char[] s1 = "123456789".toCharArray();
        char[] s2 = "ABCDEFGHI".toCharArray();

        t1 = new Thread(() -> {
            for (char c: s1) {
                System.out.print(c);
                LockSupport.unpark(t2); // 叫醒t2
                LockSupport.park(); // t1 阻塞 当前线程阻塞
            }
        }, "t1");

        t2 = new Thread(() -> {
            for (char c : s2) {
                LockSupport.park();
                System.out.print(c);
                LockSupport.unpark(t1);
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
