package com.moon.arithmetic.linkedlist;

/**
 * 数组下标越大，表示的越新
 *
 * @author yujiangtao
 * @date 2020/9/25 下午3:28
 */
public class LRUArray<K, V> {

    /**
     * 数组的初始大小
     */
    private final int initSize;

    private Node<K, V>[] nodes;

    private int index = -1;

    public LRUArray(int initSize) {
        this.initSize = initSize;
        nodes = new Node[initSize];
    }

    public void add(K key, V value) {
        int existIndex = findKey(key);
        Node<K, V> newNode = new Node<>(key, value);
        // 表示当前 key 不存在，直接新增
        if(existIndex == -1) {
            if(index < initSize - 1) {
                index ++;
                nodes[index] = newNode;
            } else {
                // 淘汰下标为 0 的数据
                Node<K, V>[] newNodes = new Node[initSize];
                System.arraycopy(nodes, 1, newNodes, 0, index);
                newNodes[index] = newNode;
                nodes = newNodes;
            }
        }
        // 当前 key 存在
        else {
            // 淘汰当前 key，并新增当前 key
            Node<K, V>[] newNodes = new Node[initSize];
            System.arraycopy(nodes, 0, newNodes, 0, existIndex);
            System.arraycopy(nodes, existIndex + 1, newNodes, existIndex, index - existIndex);
            newNodes[index] = newNode;
            nodes = newNodes;
        }
    }

    public void printAllNodes() {
        if(index == 0) {
            System.out.println("empty array");
            return;
        }
        System.out.println("++++++++++++++++++++++++++++");
        for(int i = 0; i <= index; i++) {
            System.out.printf("{%s: %s} -> ", nodes[i].key, nodes[i].value);
        }
        System.out.println();
    }

    public int getIndex() {
        return this.index;
    }

    /**
     * 寻找数组中是否存在当前 key
     * @param key
     * @return -1 表示不存在
     */
    private int findKey(K key) {
        if(key instanceof String) {
            for(int i = 0; i <= index; i++) {
                if(nodes[i].key.equals((String) key)) {
                    return i;
                }
            }
        }
        return -1;
    }


    private static class Node<K, V> {

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
