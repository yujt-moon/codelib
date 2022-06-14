package com.moon.multithread;

/**
 * 如何让两个线程依次执行？
 *
 * @author yujiangtao
 * @date 2021/5/21 上午10:50
 */
public class SequentialPrint {

    public static void main(String[] args) {

        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("ThreadA: " + i);
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                threadA.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("ThreadB: " + i);
            }
        });

        threadA.start();
        threadB.start();
    }
}
