package com.moon.arithmetic.queue;

import org.junit.Test;

/**
 * @author yujiangtao
 * @date 2020/10/11 下午3:59
 */
public class ArrayQueueTest {

    @Test
    public void testEnqueue() {
        ArrayQueue<String> queue = new ArrayQueue<>(5);
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.enqueue("5");
        queue.enqueue("6");
        queue.printQueue();
    }

    @Test
    public void testDequeue() {
        ArrayQueue<String> queue = new ArrayQueue<>(5);
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.enqueue("5");
        queue.enqueue("6");
        queue.printQueue();


        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.printQueue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.printQueue();

        queue.enqueue("7");
        queue.printQueue();

        queue.enqueue("8");
        queue.enqueue("9");
        queue.enqueue("10");
        queue.printQueue();
    }
}
