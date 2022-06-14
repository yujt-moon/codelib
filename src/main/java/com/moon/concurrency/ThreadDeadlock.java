package com.moon.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程饥饿死锁（Thread Starvation Deadlock）
 * @author yujiangtao
 * @date 2019/3/16 12:48
 */
public class ThreadDeadlock {

    ExecutorService exec = Executors.newSingleThreadExecutor();

    public class RenderPageTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            /*Future<String> header, footer;
            header = exec.submit(new LoadFileTask("header.html"));
            footer = exec.submit(new LoadFileTask("footer.html"));
            String page = renderBody();
            // 将发生死锁 ---- 由于任务在等待子任务的结果
            return header.get() + page + footer.get();*/

            
            return null;
        }

        private String renderBody() {
            return null;
        }

        private class LoadFileTask implements Runnable {
            public LoadFileTask(String s) {
            }

            @Override
            public void run() {

            }
        }
    }
}

