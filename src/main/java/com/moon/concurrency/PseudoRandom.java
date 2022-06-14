package com.moon.concurrency;

/**
 * PseudoRandom
 * @author yujiangtao
 * @date 2019/3/22 16:18
 */
public class PseudoRandom {

    int caculateNext(int prev) {
        prev ^= prev << 6;
        prev ^= prev >>> 21;
        prev ^= prev << 7;
        return prev;
    }
}
