package com.moon.concurrency;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Michael-Scott(Michael and Scott, 1996)非阻塞算法中的插入算法
 * @author yujiangtao
 * @date 2019/3/22 17:20
 */
public class LinkedQueue<E> {

    // 哑节点（dummy）
    private final Node<E> dummy = new Node<E>(null, null);

    private final AtomicReference<Node<E>> head = new AtomicReference<>(dummy);

    private final AtomicReference<Node<E>> tail = new AtomicReference<>(dummy);

    /**
     * 多线程中无问题
     * （插入尾部有两步操作，将当前的元素的next指向新元素，更新新元素为尾节点）
     * @param item
     * @return
     */
    public boolean put(E item) {
        Node<E> newNode = new Node<E>(item, null);
        while(true) {
            Node<E> curTail = tail.get();
            Node<E> tailNext = curTail.next.get();
            if(curTail == tail.get()) {
                if(tailNext != null) {
                    // 队列处于中间状态，推进尾节点
                    tail.compareAndSet(curTail, tailNext);
                } else {
                    // 处于稳定状态，尝试插入新节点
                    if(curTail.next.compareAndSet(null, newNode)) {
                        // 插入操作成功，尝试推进尾节点
                        tail.compareAndSet(curTail, newNode);
                        return true;
                    }
                }
            }
        }
    }

    private static class Node<E> {
        final E item;
        final AtomicReference<Node<E>> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<>(next);
        }
    }
}
