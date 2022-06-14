package com.moon.concurrency;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * @author yujiangtao
 * @date 2019/2/14 22:09
 */
public class Indexer implements Runnable {

    private final BlockingQueue<File> queue;

    public Indexer(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while(true) {
                indexFile(queue.take());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void indexFile(File file) {

    }
}
