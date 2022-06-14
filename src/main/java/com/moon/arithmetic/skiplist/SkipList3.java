package com.moon.arithmetic.skiplist;

import java.util.Random;

/**
 * 跳跃表（自己实现）
 * 从小到大排列
 *
 * @author yujiangtao
 * @date 2021/3/16 上午11:03
 */
public class SkipList3 {

    /**
     * 跳表最大层级
     */
    private static int MAX_LEVEL = 16;

    /**
     * 默认开始时的层级
     */
    private int highLevel = 1;

    /**
     * 哨兵节点
     */
    Node head = new Node(Integer.MIN_VALUE);

    /**
     * 插入跳表
     * @param value
     * @return
     */
    public boolean insert(int value) {
        // 将要插入的节点
        Node newNode = new Node(value);
        int level = randLevel();

        for (int i = level; i >= 1; i--) {
            // 从头节点开始寻找，可以插入的位置（每次寻找，失去了跳表的快速定位功能）
            Node temp = head;
            // 与当前节点的后一个进行比较（这样可以定位前一个节点与后一个节点）
            while(temp.nexts[i-1] != null && temp.nexts[i-1].data < value) {
                temp = temp.nexts[i-1];
            }
            if(temp.nexts[i-1] == null) {
                temp.nexts[i-1] = newNode;
            } else {
                newNode.nexts[i-1] = temp.nexts[i-1];
                temp.nexts[i-1] = newNode;
            }
        }

        if(level > this.highLevel) {
            this.highLevel = level;
        }
        return true;
    }

    /**
     * 插入跳表
     * @param value
     * @return
     */
    public boolean insertOptim(int value) {
        // 将要插入的节点
        Node newNode = new Node(value);
        int level = randLevel();

        // 从头节点开始寻找，可以插入的位置
        Node temp = head;
        for (int i = level; i >= 1; i--) {
            // 与当前节点的后一个进行比较（这样可以定位前一个节点与后一个节点）
            while(temp.nexts[i-1] != null && temp.nexts[i-1].data < value) {
                temp = temp.nexts[i-1];
            }
            if(temp.nexts[i-1] == null) {
                temp.nexts[i-1] = newNode;
            } else {
                newNode.nexts[i-1] = temp.nexts[i-1];
                temp.nexts[i-1] = newNode;
            }
        }

        if(level > this.highLevel) {
            this.highLevel = level;
        }
        return true;
    }

    public boolean delete(int value) {
        boolean isDeleted = false;
        for (int i = highLevel; i >= 1; i--) {
            Node temp = head;
            while(temp.nexts[i-1] != null && temp.nexts[i-1].data < value) {
                temp = temp.nexts[i-1];
            }
            if(temp.nexts[i-1] != null && temp.nexts[i-1].data == value) {
                temp.nexts[i-1] = temp.nexts[i-1].nexts[i-1];
                isDeleted = true;
            }
        }
        // 判断各个层级是否还有元素
        for (int i = highLevel; i > 1 ; i--) {
            if(head.nexts[i-1] == null) {
                highLevel --;
            }
        }
        return isDeleted;
    }

    /**
     * 优化删除
     * @param value
     * @return
     */
    public boolean deleteOptim(int value) {
        boolean isDeleted = false;
        Node temp = head;
        for (int i = highLevel; i >= 1; i--) {
            while(temp.nexts[i-1] != null && temp.nexts[i-1].data < value) {
                temp = temp.nexts[i-1];
            }
            if(temp.nexts[i-1] != null && temp.nexts[i-1].data == value) {
                temp.nexts[i-1] = temp.nexts[i-1].nexts[i-1];
                isDeleted = true;
            }
        }
        // 判断各个层级是否还有元素
        for (int i = highLevel; i > 1 ; i--) {
            if(head.nexts[i-1] == null) {
                highLevel --;
            }
        }
        return isDeleted;
    }

    public void printSkipList() {
        for (int i = highLevel; i >= 1; i--) {
            Node temp = head;
            do {
                if(temp == head) {
                    System.out.printf("%-9s", "head --> ");
                } else {
                    System.out.printf("%-9s", (temp.data + " -->"));
                }
                temp = temp.nexts[i-1];
            } while (temp != null);
            System.out.println();
        }
    }

    /**
     * 查找元素所在的节点
     * @param value
     * @return
     */
    public Node search(int value) {
        Node temp = head;
        for (int i = highLevel; i >= 1; i--) {
            while(temp.nexts[i-1] != null && temp.nexts[i-1].data < value) {
                temp = temp.nexts[i-1];
            }
            if(temp.nexts[i-1] != null && temp.nexts[i-1].data == value) {
                return temp.nexts[i-1];
            }
        }
        return null;
    }


    /**
     * 随机产生层数
     * @return
     */
    private int randLevel() {
        int level = 1;
        Random r = new Random();
        while(r.nextInt() % 2 == 0 && level < MAX_LEVEL) {
            level ++;
        }
        return level;
    }


    private static class Node {
        /**
         * 数据信息
         */
        private int data;

        /**
         * 当前节点对应的各个层级的下一个元素
         */
        private Node[] nexts;

        public Node(int data) {
            this.data = data;
            this.nexts = new Node[MAX_LEVEL];
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }


    public static void main(String[] args) {
        SkipList3 skipList3 = new SkipList3();
//        skipList3.insert(7);
//        skipList3.insert(10);
//        skipList3.insert(4);
//        skipList3.printSkipList();
//        System.out.println("=========================");
//
//        skipList3.delete(7);
//        skipList3.printSkipList();
//        System.out.println("=========================");
//
//        skipList3.delete(10);
//        skipList3.printSkipList();
//        System.out.println("=========================");

        skipList3.insertOptim(7);
        skipList3.insertOptim(1);
        skipList3.insertOptim(10);
        skipList3.insertOptim(3);
        skipList3.printSkipList();
        System.out.println("================================");

        skipList3.insertOptim(5);
        skipList3.printSkipList();
        System.out.println("===================================");

        skipList3.deleteOptim(5);
        skipList3.printSkipList();

        Node search = skipList3.search(3);
        System.out.println(search);

        Node search2 = skipList3.search(9);
        System.out.println(search2);

        System.out.println();
    }
}
