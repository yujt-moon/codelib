package com.moon.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * instruct reorder might cause strange output (0, 0)
 * @author yujiangtao
 * @date 2019/3/23 13:06
 */
public class PossibleReordering2 {
    static int x = 0, y = 0;
    static int a = 0, b = 0;

    public static void main(String[] args)
            throws InterruptedException {

        for (long i = 0; i < Long.MAX_VALUE; i++) {

            x = 0;
            y = 0;
            a = 0;
            b = 0;

            CountDownLatch latch = new CountDownLatch(2);

            Thread one = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 由于以下两个变量没有相关性，会出现指令重排序
                    a = 1;
                    x = b;
                    latch.countDown();
                }
            });

            Thread other = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 由于以下两个变量没有相关性，会出现指令重排序
                    b = 1;
                    y = a;
                    latch.countDown();
                }
            });

            one.start();
            other.start();
            latch.await();
            if(x == 0 && y == 0) {
                System.err.println("第" + i + "次为：" + "( " + x + "," + y + " )");
                break;
            }
        }
    }
}
