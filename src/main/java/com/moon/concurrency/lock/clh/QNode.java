package com.moon.concurrency.lock.clh;

/**
 * @author yujiangtao
 * @date 2020/12/22 下午2:35
 */
public class QNode {
    // 默认不需要锁
    volatile boolean locked = false;
}
