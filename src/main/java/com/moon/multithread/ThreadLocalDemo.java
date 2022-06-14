package com.moon.multithread;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 一个线程设值，另一个线程取值，取不到
 *
 * @author yujiangtao
 * @date 2020/8/24 下午9:57
 */
public class ThreadLocalDemo {

    static ThreadLocal<String> tl = new ThreadLocal<>();

    public static void main(String[] args) throws IOException {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(tl.get());
        }).start();


        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            tl.set("haha");


            String s = tl.get();
            System.out.println(s);
        }).start();
    }
}
