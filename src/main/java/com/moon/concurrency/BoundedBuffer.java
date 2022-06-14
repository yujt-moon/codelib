package com.moon.concurrency;

import java.util.concurrent.Semaphore;

/**
 * 使用信号量创建一个有界容器
 * @author yujiangtao
 * @date 2019/3/20 12:41
 */
public class BoundedBuffer<E> {

    /**
     * 用来表示拥有的有数据，有空位
     */
    private final Semaphore availableItems, availableSpaces;

    /**
     * 容器底层，数组，保存数据
     */
    private final E[] items;

    /**
     * 存放元素的位置，和提取元素的位置
     */
    private int putPosition = 0, takePosition = 0;

    /**
     * 根据容量构造有界容器
     * @param capacity
     */
    public BoundedBuffer(int capacity) {
        availableItems = new Semaphore(0);
        availableSpaces = new Semaphore(capacity);
        items = (E[]) new Object[capacity];
    }

    /**
     * 容器是否为空
     * @return
     */
    public boolean isEmpty() {
        return availableItems.availablePermits() == 0;
    }

    /**
     * 容器是否为满
     * @return
     */
    public boolean isFull() {
        return availableSpaces.availablePermits() == 0;
    }

    /**
     * 向容器中存放数据（阻塞方法：如果容器满，则阻塞）
     * @param x
     * @throws InterruptedException
     */
    public void put(E x) throws InterruptedException {
        availableSpaces.acquire();
        doInsert(x);
        availableItems.release();
    }

    /**
     * 从容器中提取数据（阻塞方法：如果容器为空，则阻塞）
     * @return
     * @throws InterruptedException
     */
    public E take() throws InterruptedException {
        availableItems.acquire();
        E item = doExtract();
        availableSpaces.release();
        return item;
    }

    /**
     * 将数据插入到容器中
     * @param x
     */
    private synchronized void doInsert(E x) {
        int i = putPosition;
        items[i] = x;
        // 一直加，到尾部直接回头加起
        putPosition = (++i == items.length) ? 0 : i;
    }

    /**
     * 从容器中提取数据
     * @return
     */
    private synchronized E doExtract() {
        int i = takePosition;
        E x = items[i];
        items[i] = null;
        //  This makes me confused?
        // It takes time to understand this. 一直加，到尾部直接回头加起
        takePosition = (++i == items.length) ? 0 : i;
        return x;
    }
}
