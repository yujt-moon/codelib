package com.moon.jvm;

import java.util.Random;

/**
 *
 * cmd: vmstat 1 4
 * @author yujiangtao
 * @date 2021/5/11 下午11:44
 */
public class HoldLockMain {

    public static Object[] lock = new Object[10];

    public static Random r = new Random();

    static {
        for (int i = 0; i < lock.length; i++) {
            lock[i] = new Object();
        }
    }

    // 一个持有锁的线程
    public static class HoldLockTest implements Runnable {

        private int i;

        public HoldLockTest(int i) {
            this.i = i;
        }

        public static void main(String[] args) {
            for (int i = 0; i < lock.length * 2; i++) {
                // 每两个线程，使用同一个锁对象
                new Thread(new HoldLockTest(i/2)).start();
            }
        }

        @Override
        public void run() {
            try {
                while(true) {
                    // 持有锁
                    synchronized (lock[i]) {
                        // 等待
                        if(i % 2 == 0) {
                            lock[i].wait(r.nextInt(10));
                        } else { // 通知
                            lock[i].notifyAll();
                        }
                    }
                }
            } catch (Exception e) {

            }
        }
    }
}
