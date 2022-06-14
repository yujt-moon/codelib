package com.moon.concurrency;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author yujiangtao
 * @date 2019/3/10 15:14
 */
public class LogWriter {
    private final BlockingQueue<String> queue;
    private final LoggerTread logger;

    public static final int CAPACITY = 100;

    public LogWriter(PrintWriter writer) {
        this.queue = new LinkedBlockingQueue<>(CAPACITY);
        this.logger = new LoggerTread(writer);
    }

    public void start() {
        logger.start();
    }

    public void log(String msg) throws InterruptedException {
        queue.put(msg);
    }

    private class LoggerTread {
        private final PrintWriter writer;

        public LoggerTread(PrintWriter writer) {
            this.writer = writer;
        }

        public void start() {

        }

        public void run() {
            try {
                while(true) {
                    writer.println(queue.take());
                }
            } catch (InterruptedException ignored) {

            } finally {
                writer.close();
            }
        }
    }
}


