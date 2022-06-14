package com.moon.concurrency;

import java.math.BigInteger;

/**
 * 昂贵的计算方法
 * @author yujiangtao
 * @date 2019/2/17 14:36
 */
public class ExpensiveFunction implements Computable<String, BigInteger> {

    public BigInteger compute(String arg) throws InterruptedException {
        // 经过长时间计算后
        return new BigInteger(arg);
    }
}
