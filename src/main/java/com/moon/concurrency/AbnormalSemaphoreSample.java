package com.moon.concurrency;

import java.util.concurrent.Semaphore;

/**
 * @author yujiangtao
 * @date 2020/11/5 下午5:52
 */
public class AbnormalSemaphoreSample {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new MyWorker(semaphore));
            t.start();
        }
        System.out.println("Action ... GO!");
        semaphore.release(5);
        System.out.println("Wait for permits off");
        while(semaphore.availablePermits() != 0) {
            Thread.sleep(100L);
        }
        System.out.println("Action ... GO again!");
        semaphore.release(5);
    }
}

class MyWorker implements Runnable {

    private Semaphore semaphore;

    public MyWorker(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println("Executed!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
