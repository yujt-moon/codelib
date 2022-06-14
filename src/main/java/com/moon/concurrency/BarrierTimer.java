package com.moon.concurrency;

/**
 * @author yujiangtao
 * @date 2019/3/20 17:17
 */
public class BarrierTimer implements Runnable {

    private boolean started;

    private long startTime, endTime;

    @Override
    public synchronized void run() {
        long t = System.nanoTime();
        if(!started) {
            started = true;
            startTime = t;
        } else {
            endTime = t;
        }
    }

    public synchronized void clear() {
        started = false;
    }

    public synchronized long getTime() {
        return endTime - startTime;
    }
}
