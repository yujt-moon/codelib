package com.moon.jvm;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * 读取对象头信息
 *
 * -XX:BiasedLockingStartupDelay=0
 *
 * -XX:+PrintFlagsInitial
 *
 * -XX:+UseCompressedOops
 *
 * @author yujiangtao
 * @date 2020/10/22 下午8:45
 */
public class HeaderInfo {

    public static void main(String[] args) {
        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
        synchronized (object) {
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }
        System.out.println("------------------------------------------------------");
        System.out.printf("hashCode 为：%x\n", object.hashCode());
        System.out.println("=====================================================");
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
        System.gc();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }

    @Test
    public void lightWeightLock() throws Exception {
        Object object = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (object) {
                System.out.println("thread1 locking!");
                System.out.println(ClassLayout.parseInstance(object).toPrintable());
            }

            try {
                // 线程还未结束
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (object) {
                System.out.println("thread2 locking");
                System.out.println(ClassLayout.parseInstance(object).toPrintable());
            }
        });

        thread1.start();
        // 等待锁结束
        TimeUnit.SECONDS.sleep(3);

        thread2.start();
    }

    @Test
    public void heavyWeightLock() throws Exception {
        Object object = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (object) {
                System.out.println("thread1 locking!");
                System.out.println(ClassLayout.parseInstance(object).toPrintable());
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (object) {
                System.out.println("thread2 locking");
                System.out.println(ClassLayout.parseInstance(object).toPrintable());
            }
        });

        thread1.start();
        thread2.start();
        TimeUnit.SECONDS.sleep(4);
    }
}
