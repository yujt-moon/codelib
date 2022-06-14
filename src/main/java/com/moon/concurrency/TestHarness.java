package com.moon.concurrency;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * 闭锁（启动多个线程执行某项任务，所有线程都启动后开始执行某项任务，
 * 记录开始时间，所有任务都完成后记录记录结束时间）
 * @author yujiangtao
 * @date 2019/2/17 13:23
 */
public class TestHarness {
    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException ignored) {

                    }
                }
            };
            t.start();
        }

        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                int randNum = random.nextInt(10);
                System.out.println("当前线程" + Thread.currentThread().getId() + "耗时：" + randNum + "秒");
                try {
                    Thread.sleep(randNum * 1000);
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
            }
        };

        try {
            Long time = new TestHarness().timeTasks(5, runnable);
            System.out.println("总共耗时：" + time/1_000_000_000 + "秒");
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }
}
