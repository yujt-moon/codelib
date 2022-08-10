package com.moon.concurrency;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 任务提交顺序不对，打印也会不对
 *
 * @author yujiangtaoa
 * @date 2022/6/17 上午11:21
 */
public class SequenceABC {

    private Lock lock = new ReentrantLock();

    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void executeA() {
        try {
            lock.lock();
            System.out.println("A");
            conditionB.signalAll();
            conditionA.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void executeB() {
        try {
            lock.lock();
            System.out.println("B");
            conditionC.signalAll();
            conditionB.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void executeC() {
        try {
            lock.lock();
            System.out.println("C");
            conditionA.signalAll();
            conditionC.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Test
    public void printABC() throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        SequenceABC sequenceABC = new SequenceABC();

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                for (;;) {
                    sequenceABC.executeA();
                }
            }
        });

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                for (;;) {
                    sequenceABC.executeB();
                }
            }
        });

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                for (;;) {
                    sequenceABC.executeC();
                }
            }
        });


        System.in.read();
    }
}
