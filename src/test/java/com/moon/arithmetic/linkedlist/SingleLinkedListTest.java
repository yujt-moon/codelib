package com.moon.arithmetic.linkedlist;

import org.junit.Test;

/**
 * @author yujiangtao
 * @date 2020/10/10 上午11:02
 */
public class SingleLinkedListTest {

    @Test
    public void testReverse() {
        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("4");

        linkedList.printNodes();
        linkedList.reverse();
        linkedList.printNodes();
    }
}
