package com.moon.concurrency;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * @author yujiangtao
 * @date 2019/3/10 13:38
 */
public class PrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;

    PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while(!Thread.currentThread().isInterrupted()) {
                queue.put(p = p.nextProbablePrime());
            }
        } catch (InterruptedException e){
            /* 允许线程退出 */
        }
    }

    public void cancel() {
        interrupt();
    }
}
