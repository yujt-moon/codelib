package com.moon.concurrency;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 使用 Treiber 算法（Treiber,1986）构造的非阻塞的栈
 * @author yujiangtao
 * @date 2019/3/22 16:49
 */
public class ConcurrentStack<E> {

    AtomicReference<Node<E>> top = new AtomicReference<>();

    public void push(E item) {
        Node<E> newHead = new Node<E>(item);
        Node<E> oldHead;
        do {
            oldHead = top.get();
            newHead.next = oldHead;
        } while(!top.compareAndSet(oldHead, newHead));
    }

    public E pop() {
        Node<E> oldHead;
        Node<E> newHead;
        do {
            oldHead = top.get();
            if(oldHead == null) {
                return null;
            }
            newHead = oldHead.next;
        } while(!top.compareAndSet(oldHead, newHead));
        return oldHead.item;
    }

    private static class Node<E> {
        public final E item;

        public Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }
}
