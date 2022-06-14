package com.moon.concurrency;

/**
 * 会产生不可预知的问题（实际上依靠于readerThread的启动时机与赋值的时机）
 * @author yujiangtao
 * @date 2019/2/5 16:29
 */
public class NoVisibility {
    private static boolean ready;

    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}
