package com.moon.multithread;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author yujiangtao
 * @date 2021/5/6 上午10:25
 */
public class InheritableThreadLocalDemo {

    @Test
    public void normalThreadLocal() {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        IntStream.range(0, 10).forEach(i -> {
            // 每个线程的序列号，希望在子线程中能够拿到
            threadLocal.set(i);
            // 这里来了一个子线程，我们希望可以访问上面的 threadLocal
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
            }).start();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void inheritableThreadLocal() {
        InheritableThreadLocal<Integer> inheritableThreadLocal = new InheritableThreadLocal<>();
        IntStream.range(0, 10).forEach(i -> {
            // 每个线程的序列号，希望在子线程中能够拿到
            inheritableThreadLocal.set(i);
            // 这里来了一个子线程，我们希望可以访问上面的 threadLocal
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + ":" + inheritableThreadLocal.get());
            }).start();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
