package com.moon.jvm;

/**
 * @author yujiangtao
 * @date 2020/9/28 下午2:14
 */
public class OutOfOrder {

    static volatile int i = 0, j = 0;

    static void one() {
        i++;
        j++;
    }

    static void two() {
        if(j > i) {
            System.out.println("i=" + i + " j=" + j);
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    one();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    two();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
