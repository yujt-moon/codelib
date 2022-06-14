package com.moon.concurrency;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 网络爬虫
 * @author yujiangtao
 * @date 2019/3/16 12:01
 */
public abstract class WebCrawler {

    private volatile TrackingExecutor exec;

    private final Set<URL> urlsToCrawl = new HashSet<>();

    /**
     * 超时时间
     */
    private final Long TIMEOUT = 3000L;

    /**
     * 时间单元（表示时间的单位 ）
     */
    private final TimeUnit UNIT = TimeUnit.MILLISECONDS;

    public synchronized void start() {
        exec = new TrackingExecutor(Executors.newCachedThreadPool());
        for(URL url : urlsToCrawl) {
            submitCrawlTask(url);
        }
        urlsToCrawl.clear();
    }

    public synchronized void stop() throws InterruptedException {
        try {
            saveUncrawled(exec.shutdownNow());
            if(exec.awaitTermination(TIMEOUT, UNIT)) {}
        } finally {

        }
    }

    private void submitCrawlTask(URL u) {
        exec.execute(new CrawlTask(u));
    }

    private void saveUncrawled(List<Runnable> uncrawled) {
        for(Runnable task : uncrawled) {
            urlsToCrawl.add(((CrawlTask) task).getPage());
        }
    }

    private class CrawlTask implements Runnable {
        private final URL url;

        public CrawlTask(URL url) {
            this.url = url;
        }

        @Override
        public void run() {
            for(URL link : processPage(url)) {
                if(Thread.currentThread().isInterrupted()) {
                    return;
                }
                submitCrawlTask(link);
            }
        }

        public URL getPage() {
            return url;
        }
    }

    protected abstract List<URL> processPage(URL url);
}
