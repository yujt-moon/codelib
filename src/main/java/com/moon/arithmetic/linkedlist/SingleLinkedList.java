package com.moon.arithmetic.linkedlist;

/**
 * 单链表
 *
 * @author yujiangtao
 * @date 2020/10/10 上午10:33
 */
public class SingleLinkedList<T> {

    private Node head;

    public void add(T item) {
        Node newNode = new Node(item);
        if(head == null) {
            head = newNode;
            return;
        }
        Node cur = head;
        while(cur.next != null) {
            cur = cur.next;
        }
        cur.next = newNode;
    }

    public void clear() {
        head = null;
        head.next = null;
    }

    public void reverse() {
        Node curr = head, pre = null;
        while(curr != null) {
            Node next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        head = pre;
    }

    public void printNodes() {
        if(head == null) {
            System.out.println("empty linked list");
            return;
        }
        Node curr = head;
        while(curr != null) {
            System.out.printf("[ %s ] -> ", curr.item);
            curr = curr.next;
        }
        System.out.println();
    }

    private static class Node<T> {
        private T item;

        private Node next;

        public Node(T item) {
            this.item = item;
        }
    }
}
