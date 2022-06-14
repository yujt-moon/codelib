package com.moon.concurrency;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author yujiangtao
 * @date 2019/3/10 13:21
 */
public class BrokenPrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;

    private volatile boolean cancelled = false;

    BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while(!cancelled) {
                queue.put(p = p.nextProbablePrime());
            }
        } catch (InterruptedException e){

        }
    }

    public void cancel() {
        cancelled = true;
    }


    void consumePrimes() throws InterruptedException {
        BlockingQueue<BigInteger> primes = new LinkedBlockingQueue<BigInteger>();
        BrokenPrimeProducer producer = new BrokenPrimeProducer(primes);
        producer.start();

        try {
            while(needMorePrimes()) {
                consume(primes.take());
            }
        } finally {
            producer.cancel();
        }

    }

    private void consume(BigInteger take) {
    }

    private boolean needMorePrimes() {
        return true;
    }
}
