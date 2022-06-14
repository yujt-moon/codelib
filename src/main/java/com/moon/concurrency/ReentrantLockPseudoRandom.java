package com.moon.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于 ReentrantLock 实现的随机数生成器
 * @author yujiangtao
 * @date 2019/3/22 16:16
 */
public class ReentrantLockPseudoRandom extends PseudoRandom {

    private final Lock lock = new ReentrantLock(false);

    private int seed;

    ReentrantLockPseudoRandom(int seed) {
        this.seed = seed;
    }

    public int nextInt(int n) {
        lock.lock();
        try {
            int s = seed;
            seed = caculateNext(seed);
            int reminder = s % n;
            return reminder > 0 ? reminder : reminder + n;
        } finally {
            lock.unlock();
        }
    }
}
