package com.moon.concurrency;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/**
 * @author yujiangtao
 * @date 2019/3/10 15:48
 */
public class IndexingService {
    private static final File POISON = new File("");
    private final IndexerThread consumer = new IndexerThread();
    private final CrawelerThread producer = new CrawelerThread();
    private final BlockingQueue<File> queue;
    private final FileFilter fileFilter;
    private final File root;

    public IndexingService(BlockingQueue<File> queue, FileFilter fileFilter, File root) {
        this.queue = queue;
        this.fileFilter = fileFilter;
        this.root = root;
    }

    public void start() {
        producer.start();
        consumer.start();
    }

    public void stop() {
        producer.interrupt();
    }

    public void awaitTermination() throws InterruptedException {
        consumer.join();
    }


    class CrawelerThread extends Thread {
        private File root;

        @Override
        public void run() {
            try {
                crawel(root);
            } catch (InterruptedException e) {
                /* 发生异常 */
            } finally {
                while(true) {
                    try {
                        queue.put(POISON);
                        break;
                    } catch (InterruptedException e) {
                        /* 重新尝试 */
                    }
                }
            }
        }

        private void crawel(File file) throws InterruptedException {

        }

    }

    class IndexerThread extends Thread {
        @Override
        public void run() {
            try {
                while(true) {
                    File file = queue.take();
                    if(file == POISON) {
                        break;
                    } else {
                        indexFile(file);
                    }
                }
            } catch (InterruptedException consumed) {

            }
        }

        private void indexFile(File file) {

        }
    }
}
