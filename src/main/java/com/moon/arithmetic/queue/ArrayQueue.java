package com.moon.arithmetic.queue;


/**
 * 数组实现队列
 *
 * @author yujiangtao
 * @date 2020/10/11 下午3:41
 */
public class ArrayQueue<T> {

    private T[] items;

    private int capacity;

    private int head = 0;

    private int tail = 0;

    public ArrayQueue(int capacity) {
        items = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    /**
     * 入队
     * @param t
     * @return
     */
    public boolean enqueue(T t) {
        if(tail == capacity) {
            if(head != 0) {
                // 移动元素
                for (int i = 0; i < tail - head; i++) {
                    items[i] = items[head+i];
                }
                tail = tail - head;
                head = 0;
                enqueue(t);
            }
            return false;
        }
        items[tail] = t;
        tail++;
        return true;
    }

    /**
     * 出队
     * @return
     */
    public T dequeue() {
        if(head == tail) {
            return null;
        }
        T temp = items[head];
        head ++;
        return temp;
    }

    public void printQueue() {
        if(head == tail) {
            System.out.println("empty queue!");
            return;
        }
        for (int i = head; i < tail; i++) {
            System.out.printf("[ %s ] -> ", items[i]);
        }
        System.out.println();
    }
}
