package com.moon.concurrency.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author yujiangtao
 * @date 2020/12/22 下午4:21
 */
public class SpinLockTest {

    public static void main(String[] args) throws Exception {
        SpinLock spinLock = new SpinLock();
        spinLock.lock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                spinLock.lock();
                spinLock.unlock();
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        spinLock.unlock();
    }
}