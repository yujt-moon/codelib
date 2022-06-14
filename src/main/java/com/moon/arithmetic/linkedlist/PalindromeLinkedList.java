package com.moon.arithmetic.linkedlist;

/**
 * 回文链表
 *
 * <p>如果字符串是通过单链表来存储的，那该如何来判断是一个回文串呢？</p>
 * <p>   你有什么好的解决思路呢？</p>
 * <p>   相应的时间空间复杂度又是多少呢？</p>
 *
 * <ol>
 *  <li>快慢指针定位中间节点</li>
 *  <li>从中间节点对后半部分逆序</li>
 *  <li>前后半部分比较，判断是否为回文</li>
 *  <li>后半部分逆序复原</li>
 * </ol>
 * @author yujiangtao
 * @date 2020/9/27 上午11:37
 */
public class PalindromeLinkedList {

    private Node head;

    public PalindromeLinkedList(String initString) {
        Node curr = null;
        for (int i = 0; i < initString.length(); i++) {
            if(i == 0) {
                head = new Node(initString.charAt(0));
                curr = head;
            } else {
                Node newNode = new Node(initString.charAt(i));
                curr.next = newNode;
                curr = newNode;
            }
        }
    }

    public void printAllNodes() {
        if(head == null) {
            System.out.println("empty linked list");
        } else {
            for (Node cur = head; cur != null; cur = cur.next) {
                System.out.printf("%s -> ", cur.item);
            }
        }
        System.out.println();
    }

    public void printAllNodes(Node assigned) {
        if(head == null) {
            System.out.println("empty linked list");
        } else {
            for (Node cur = head; cur != null; cur = cur.next) {
                if(cur == assigned) {
                    System.out.printf("[%s] -> ", cur.item);
                } else {
                    System.out.printf("%s -> ", cur.item);
                }
            }
        }
        System.out.println();
    }

    /**
     * 该字符串是否是回文字符串
     * @return
     */
    public boolean isPalindrome() {
        // 寻找中间节点
        Node slowPointer = null;
        Node fastPointer = null;
        boolean isEven = false;
        Node mediumPointer = null;

        slowPointer = head;
        mediumPointer = head;
        if(head != null && head.next != null) {
            fastPointer = head.next;
        } else {
            return true;
        }

        while (true) {
            // 移动快指针，还没到结尾
            if(fastPointer.next != null && fastPointer.next.next != null) {
                // 逆序前一半链表，链表结构将被破坏
                slowPointer = slowPointer.next;
                fastPointer = fastPointer.next.next;
                mediumPointer = slowPointer;
            }
            // 表示有奇数个节点
            else if(fastPointer.next != null && fastPointer.next.next == null) {
                slowPointer = slowPointer.next;
                mediumPointer = slowPointer;
                break;
            }
            // 已经达到中间节点，并且是偶数个节点
            else {
                isEven = true;
                break;
            }
        }

        System.out.println("slow pointer: " + slowPointer.item);
        System.out.println("fast pointer: " + fastPointer.item);
        System.out.println("medium pointer: " + mediumPointer.item);
        printAllNodes(mediumPointer);

        // 逆序链表后半截
        Node next = mediumPointer.next;
        Node leftNodeCopy = reverseLinkedList(next);
        try {
            // 剩下的链表更有可能短
            Node curr = head;
            Node leftNode = leftNodeCopy;
            while (leftNode != null) {
                if (leftNode.item != curr.item) {
                    return false;
                }
                leftNode = leftNode.next;
                curr = curr.next;
            }
        } finally {
            // 恢复链表
            Node recoverNode = reverseLinkedList(leftNodeCopy);
            mediumPointer.next = recoverNode;
            printAllNodes();
        }

        return true;
    }


    /**
     * 反转链表
     * <a href="https://blog.csdn.net/armlinuxww/article/details/104721999">
     *     漫画：如何将一个链表“逆序”？
     * </a>
     * @param head
     */
    public Node reverseLinkedList(Node head) {
        if(head == null || head.next == null) {
            return head;
        }

        Node p1 = head;
        Node p2 = head.next;
        Node p3 = null;

        p1.next = null;
        while (p2 != null) {
            p3 = p2.next;

            p2.next = p1;

            p1 = p2;
            p2 = p3;
        }
        return p1;
    }

    public Node getHead() {
        return this.head;
    }

    public static class Node {
        private char item;

        private Node next;

        public Node(char item) {
            this.item = item;
        }
    }
}
