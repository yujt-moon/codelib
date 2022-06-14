package com.moon.arithmetic.linkedlist;

/**
 * 最近最少使用（LRU） ，淘汰算法 -- 使用单链表实现
 *
 * 我们维护一个有序单链表，越靠近链表尾部的结点是越早之前访问的。当有一个新的数据被访问时，我们从链表头开始顺序遍历链表。
 *
 * 1. 如果此数据之前已经被缓存在链表中了，我们遍历得到这个数据对应的结点，并将其从原来的位置删除，然后再插入到链表的头部。
 *
 * 2. 如果此数据没有在缓存链表中，又可以分为两种情况：
 *
 *      如果此时缓存未满，则将此结点直接插入到链表的头部；
 *
 *      如果此时缓存已满，则链表尾结点删除，将新的数据结点插入链表的头部。
 *
 * @author yujiangtao
 * @date 2020/9/25 上午11:43
 */
public class LRULinkedList<K, V> {

    /**
     * 头节点
     */
    private Node<K, V> head;

    /**
     * 初始大小
     */
    private final int initSize;

    /**
     * 当前大小
     */
    private int currentSize = 0;

    public LRULinkedList(int initSize) {
        this.initSize = initSize;
    }

    /**
     *
     * @param key
     * @param value
     */
    public void add(K key, V value) {
        if(head == null) {
            head = new Node<>(key, value);
            currentSize = 1;
        } else {
            // 判断新增的节点，该节点中是否已经存在过
            Node<K, V> kvNode = existNode(key);
            Node<K, V> newHead = new Node<>(key, value);
            // 没有该节点，直接添加到头上
            if(kvNode == null) {
                if(currentSize < initSize) {
                    // 新增该节点为头节点
                    newHead.next = head;
                    head = newHead;
                    currentSize++;
                } else {
                    // 淘汰尾部节点
                    Node<K, V> tailNode = findPrevNode(null);
                    Node<K, V> prevNode = findPrevNode(tailNode);
                    if(prevNode != null) {
                        prevNode.next = null;
                    }
                    // 新增该节点为头节点
                    newHead.next = head;
                    head = newHead;
                }
            }
            // 存在该节点
            else {
                // 剔除该节点
                Node<K, V> prevNode = findPrevNode(kvNode);
                // 表示该节点是头节点
                if(prevNode == null) {
                    newHead.next = head.next;
                    head = newHead;
                } else {
                    // 删除该节点
                    prevNode.next = kvNode.next;
                    newHead.next = head;
                    head = newHead;
                }
            }

        }
    }

    /**
     * 打印所有节点
     */
    public void printAllNode() {
        if(head == null) {
            System.out.println("empty cache");
            return;
        }
        System.out.println("=================================");
        for (Node<K, V> node = head; node != null; node = node.next) {
            System.out.printf("{ %s: %s } -> ", node.key, node.value);
        }
        System.out.println("null");
    }

    /**
     * 返回存在的节点
     * @param key 节点的key
     * @return
     */
    private Node<K, V> existNode(K key) {
        for (Node<K, V> node = head; node != null; node = node.next) {
            if(key instanceof String) {
                if(node.key.equals(key)) {
                    return node;
                }
            } else if(key instanceof Number) {
                // TODO
            }
        }
        return null;
    }

    /**
     * 根据当前节点找到前一个节点
     * @param curNode
     * @return
     */
    private Node<K, V> findPrevNode(Node<K, V> curNode) {
        for (Node<K, V> node = head; node != null; node = node.next) {
            if(node.next == curNode) {
                return node;
            }
        }
        return null;
    }


    private static class Node<K, V> {

        /**
         * 表示下一个节点
         */
        private Node<K, V> next;

        /**
         * 当前节点的 key
         */
        private K key;

        /**
         * 当前节点的 value
         */
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
