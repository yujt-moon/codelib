package com.moon.concurrency;

/**
 * @author yujiangtao
 * @date 2019/3/21 17:06
 */
public class ThreadGate {
    // 条件谓词：open-since(n) (isOpen || generation > n)

    private boolean isOpen;

    private int generation;

    public synchronized void close() {
        isOpen = false;
    }

    public synchronized void open() {
        ++generation;
        isOpen = true;
        notifyAll();
    }

    // 阻塞并直到：opened-since(generation on entry)
    public synchronized void await() throws InterruptedException {
        int availiableGeneration = generation;
        while(!isOpen && availiableGeneration == generation) {
            wait();
        }
    }
}
