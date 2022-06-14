package com.moon.multithread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description: 通过N个线程顺序循环打印从0至100，如给定N=3则输出:
 * thread0: 0
 * thread1: 1
 * thread2: 2
 * thread0: 3
 * thread1: 4
 * .....
 *
 * @author yujiangtao
 * @date 2019/3/14 17:20
 */
public class ThreadPrint {
    private int size = 4;

    public void cyclePrint() {
        for(int i = 0; i < size; i++) {
            new Thread(new PrintThread(i, size, 100)).start();
        }
    }

    /**
     * 改进的顺序打印
     */
    public void enhancedCyclePrint() {
        final Semaphore[] semaphores = new Semaphore[size];
        for (int i = 0; i < size; i++) {
            semaphores[i] = new Semaphore(1);
            // 阻塞除了第一个外的其他信号量
            if(i != 0) {
                try {
                    semaphores[i].acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        for(int i = 0; i < size; i++) {
            new Thread(new SemaphorePrintThread(semaphores, i, 100)).start();
        }
    }

    public static void main(String[] args) {
        //new ThreadPrint().cyclePrint();
        new ThreadPrint().enhancedCyclePrint();
    }
}

/**
 * 打印线程
 * 缺点：没有按次序获取锁，会造成时间的浪费（线程会多次抢占锁）
 */
class PrintThread implements Runnable {

    /**
     * 当前线程号
     */
    private int threadNo;

    /**
     * 当前将要输出的数字，内存共享
     */
    private static int currentNo = 0;

    /**
     * 所有线程抢占同一个锁
     */
    private static ReentrantLock lock = new ReentrantLock();

    /**
     * 当前线程的总数
     */
    private int size;

    /**
     * 要输出的最大的数
     */
    private int maxNo;

    public PrintThread(int threadNo, int size, int maxNo) {
        this.threadNo = threadNo;
        this.size = size;
        this.maxNo = maxNo;
    }

    public int getCurrentNo() {
        return currentNo;
    }

    public void setCurrentNo(int currentNo) {
        PrintThread.currentNo = currentNo;
    }

    @Override
    public void run() {
        while(true) {
            // 持有锁
            synchronized (lock) {
                // 达到最大值退出循环
                if(currentNo > maxNo) {
                    break;
                }

                // 判断是否轮到当前线程打印对应的数字
                if(currentNo % size == threadNo) {
                    System.out.println("Thread" + threadNo + ": " + currentNo);
                    currentNo ++;
                }
            }
        }
    }
}

/**
 * 使用信号量（优秀的解法）
 */
class SemaphorePrintThread implements Runnable {

    /**
     * 信号量
     */
    private Semaphore[] semaphores;

    /**
     * 当前线程号
     */
    private int threadNo;

    /**
     * 当前将要输出的数字，内存共享
     */
    private static int currentNo = 0;

    /**
     * 当前线程的总数
     */
    private int size;

    /**
     * 要输出的最大的数
     */
    private int maxNo;

    /**
     * 构造函数
     * @param semaphores 信号量数组
     * @param threadNo 当前的线程号
     * @param maxNo 最大的打印数字
     */
    public SemaphorePrintThread(Semaphore[] semaphores, int threadNo, int maxNo) {
        this.semaphores = semaphores;
        this.threadNo = threadNo;
        this.maxNo = maxNo;
        this.size = semaphores.length;
    }

    @Override
    public void run() {
        while(true) {
            try {
                // 获取属于当前线程的信号量（无法再次被获取，等待其他线程主动释放）
                semaphores[threadNo].acquire();
                // 阻塞其他线程后在进行相应的逻辑处理
                // 判断当前需要打印的值是否超出最大的数值
                if(currentNo > maxNo) {
                    break;
                }
                System.out.println("Thread" + threadNo + ": " + currentNo);
                currentNo ++;
                // 释放下一个线程的信号量
                // 如果达到线程的最大值，那么跳转到第一个，否则加一
                if(threadNo + 1 >= size) {
                    semaphores[0].release();
                } else {
                    semaphores[threadNo + 1].release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
