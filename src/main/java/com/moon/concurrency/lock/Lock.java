package com.moon.concurrency.lock;

/**
 * @author yujiangtao
 * @date 2020/12/22 下午2:33
 */
public interface Lock {
    void lock();

    void unlock();
}
