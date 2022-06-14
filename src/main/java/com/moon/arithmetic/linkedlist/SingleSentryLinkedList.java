package com.moon.arithmetic.linkedlist;

/**
 * 单链表（使用哨兵实现 -- 哨兵，解决的是国家之间的边界问题）
 *
 * @author yujiangtao
 * @date 2020/9/27 下午8:48
 */
public class SingleSentryLinkedList {

    /**
     * 哨兵节点
     */
    private final Node head = new Node(new Object());

    /**
     * 当前链表的大小
     */
    private int size = 0;

    /**
     * 新增链表节点
     * @param item
     */
    public void add(Object item) {
        Node curr = head;
        while(curr.next != null) {
            curr = curr.next;
        }
        curr.next = new Node(item);
        size++;
    }

    /**
     * 在指定位置插入节点
     * @param index
     * @param item
     */
    public void insert(int index, Object item) {
        if(index > size || index < 0) {
            throw new RuntimeException("位置已超过数组长度！");
        } else if(index == size) {
            add(item);
        } else {
            Node prevNode = findIndexNode(index - 1);
            Node newNode = new Node(item);
            newNode.next = prevNode.next;
            prevNode.next = newNode;
            size++;
        }
    }

    /**
     * 删除尾节点
     */
    public void delete() {
        Node curr = head;
        while(curr != null && curr.next != null && curr.next.next != null) {
            curr = curr.next;
        }
        if(curr.next != null) {
            curr.next = null;
            size--;
        }
    }

    /**
     * 找到指定位置的节点
     * @param index
     * @return
     */
    public Node findIndexNode(int index) {
        if(index >= size || index < -1) {
            throw new RuntimeException("链表中不存在当前位置");
        }
        Node curr = head;
        for (int i = 0; i <= index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    public int getSize() {
        return size;
    }

    /**
     * 链表反转
     * @return
     */
    public SingleSentryLinkedList reverse() {
        if(size < 2) {
            return this;
        }
        Node n1 = head.next;
        Node n2 = head.next.next;
        Node n3 = head.next.next.next;

        n1.next = null;
        if(size == 2) {
            n2.next = n1;
        }
        while(n3 != null) {
            Node temp = n3.next;
            n3.next = n2;
            n2.next = n1;

            n1 = n2;
            n2 = n3;
            n3 = temp;
        }
        head.next = n2;
        return this;
    }

    public void printLinkedList() {
        Node cur = head;
        while(cur != null) {
            if(cur == head) {
                System.out.print("[/] -> ");
            } else {
                System.out.printf("[%s] -> ", cur.item);
            }
            cur = cur.next;
        }
        System.out.println();
    }

    /**
     * 清空链表
     */
    public void clear() {
        head.next = null;
        size = 0;
    }

    private static class Node {
        private Object item;

        private Node next;

        public Node(Object item) {
            this.item = item;
        }
    }
}
