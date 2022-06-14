package com.moon.jvm;

/**
 * @author yujiangtao
 * @date 2021/5/12 上午12:08
 */
public class HoldCPUMain {

    public static class HoldCPUTask implements Runnable {

        @Override
        public void run() {
            while (true) {
                // 占用 cpu
                double a = Math.random() * Math.random();
            }
        }
    }

    public static class LazyTask implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    // 空闲线程
                    Thread.sleep(1000);
                }
            } catch (Exception e) {

            }
        }
    }

    public static void main(String[] args) {
        // 开启线程，占用 cpu
        new Thread(new HoldCPUTask()).start();
        // 空闲线程（总 cpu 数量 - 1）
        new Thread(new LazyTask()).start();
        new Thread(new LazyTask()).start();
        new Thread(new LazyTask()).start();
        new Thread(new LazyTask()).start();
        new Thread(new LazyTask()).start();
        new Thread(new LazyTask()).start();
        new Thread(new LazyTask()).start();
    }
}
