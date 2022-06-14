package com.moon.concurrency;

/**
 * 一个 main 函数的启动，同时有5个线程同时存在
 *
 * @author yujiangtao
 * @date 2020/11/5 下午4:10
 */
public class ThreadCount {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        ThreadGroup group = Thread.currentThread().getThreadGroup();
        while(group.getParent() != null) {
            group = group.getParent();
        }
        int total = group.activeCount();
        System.out.println("Total threads count: " + total);

        Thread[] threads = new Thread[total];
        group.enumerate(threads);
        for (int i = 0; i < threads.length; i++) {
            System.out.printf("Thread%d's name: %s.\n", i + 1, threads[i].getName());
        }
    }
}
