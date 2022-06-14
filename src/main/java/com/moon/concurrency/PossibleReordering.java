package com.moon.concurrency;

/**
 * instruct reorder might cause strange output (0, 0)
 * @author yujiangtao
 * @date 2019/3/23 13:06
 */
public class PossibleReordering {
    static int x = 0, y = 0;
    static int a = 0, b = 0;

    public static void main(String[] args)
            throws InterruptedException {
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                // 由于以下两个变量没有相关性，会出现指令重排序
                a = 1;
                x = b;
            }
        });

        Thread other = new Thread(new Runnable() {
            @Override
            public void run() {
                // 由于以下两个变量没有相关性，会出现指令重排序
                b = 1;
                y = a;
            }
        });

        one.start();
        other.start();
        one.join();
        other.join();
        System.out.println("( " + x + "," + y + " )");
    }
}
