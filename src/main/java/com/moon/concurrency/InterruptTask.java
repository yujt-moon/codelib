package com.moon.concurrency;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yujiangtao
 * @date 2019/3/10 14:06
 */
public class InterruptTask {

    private static final ScheduledExecutorService cancelExec = new ScheduledThreadPoolExecutor(10);

    public static void timeRun(final Runnable r, long timeout,
                               TimeUnit unit) throws InterruptedException {

        class RethrowableTask implements Runnable {
            private volatile  Throwable t;

            public void run() {
                try {
                    r.run();
                } catch (Throwable t) {
                    this.t = t;
                }
            }

            void rethrow() {
                if(t != null) {
                    throw ExceptionUtils.launderThrowable(t);
                }
            }
        }


        RethrowableTask task = new RethrowableTask();
        final Thread taskThread = new Thread(task);
        taskThread.start();
        cancelExec.schedule(new Runnable() {
            public void run() {
                taskThread.interrupt();
            }
        }, timeout, unit);
        taskThread.join(unit.toMillis(timeout));
        task.rethrow();
    }

}
