package com.moon.arithmetic.queue;

/**
 * @author yujiangtao
 * @date 2021/1/27 上午9:37
 */
public class CycleQueue {

    private String[] data;

    private int capacity;

    private int head = 0;

    private int tail = 0;

    public CycleQueue(int capacity) {
        this.capacity = capacity;
        this.data = new String[capacity];
    }

    /**
     * 入队
     * @param str
     * @return
     */
    public boolean enqueue(String str) {
        // 队列已满
        if((tail + 1) % capacity == head) {
            return false;
        }
        data[tail] = str;
        tail = (tail + 1) % capacity;
        return true;
    }

    /**
     * 出队
     * @return
     */
    public String dequeue() {
        if(tail == head) {
            return null;
        }
        String temp = data[head];
        head = (head + 1) % capacity;
        return temp;
    }
}
