package com.moon.concurrency.deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 注意容易发生死锁（顺序死锁）
 * @author yujiangtao
 * @date 2019/3/17 11:16
 */
public class LeftRightDeadlock {

    private final Object left = new Object();

    private final Object right = new Object();

    public void leftRight() {
        synchronized (left) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (right) {
                //doSomething();
            }
        }
    }

    public void rightLeft() {
        synchronized (right) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (left) {
                // doSomething();
            }
        }
    }

    public static void main(String[] args) {
        LeftRightDeadlock deadlock = new LeftRightDeadlock();
        ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
        Thread left = new Thread(() -> {
            deadlock.leftRight();
        });
        Thread right = new Thread(() -> {
            deadlock.rightLeft();
        });
        Runnable dlCheck = new Runnable() {
            @Override
            public void run() {
                long[] threadIds = mxBean.findDeadlockedThreads();
                if(threadIds != null) {
                    ThreadInfo[] threadInfos = mxBean.getThreadInfo(threadIds);
                    System.out.println("Detected deadlock threads:");
                    for(ThreadInfo threadInfo : threadInfos) {
                        System.out.println(threadInfo.getThreadName());
                    }
                }
            }
        };

        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        // 稍等5秒，然后每10秒进行一次死锁扫描
        service.scheduleAtFixedRate(dlCheck, 5L, 10L, TimeUnit.SECONDS);

        left.start();
        right.start();
    }
}
