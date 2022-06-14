package com.moon.multithread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 两个线程按照指定方式有序交叉运行呢？
 *
 * @author yujiangtao
 * @date 2021/5/21 上午10:56
 */
public class CrossPrint {

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(1);

        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("ThreadA: " + i);
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("ThreadB: " + i);
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}
