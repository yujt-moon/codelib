package com.moon.concurrency;

/**
 * 可计算接口
 * @author yujiangtao
 * @date 2019/2/17 14:31
 */
public interface Computable<A, V> {

    /**
     * 计算方法
     * @param arg
     * @return
     * @throws InterruptedException
     */
    V compute(A arg) throws InterruptedException;
}
