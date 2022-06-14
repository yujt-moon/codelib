package com.moon.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 基于 AtomicInteger 实现的随机数生成器
 * @author yujiangtao
 * @date 2019/3/22 16:29
 */
public class AtomicPseudoRandom extends PseudoRandom {

    private AtomicInteger seed;

    AtomicPseudoRandom(int seed) {
        this.seed = new AtomicInteger(seed);
    }

    public int nextInt(int n) {
        while(true) {
            int s = seed.get();
            int nextSeed = caculateNext(s);
            if(seed.compareAndSet(s, nextSeed)) {
                int remainder = s % n;
                return remainder > 0 ? remainder : remainder + n;
            }
        }
    }
}
