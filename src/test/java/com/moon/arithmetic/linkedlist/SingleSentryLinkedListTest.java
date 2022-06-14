package com.moon.arithmetic.linkedlist;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author yujiangtao
 * @date 2020/9/27 下午9:30
 */
public class SingleSentryLinkedListTest {

    @Test
    public void testAdd() {
        SingleSentryLinkedList single = new SingleSentryLinkedList();
        single.add("1");
        single.add("2");
        single.add("3");
        single.add("4");

        Assert.assertEquals(4, single.getSize());
    }

    @Test
    public void testInsert() {
        SingleSentryLinkedList single = new SingleSentryLinkedList();
        single.add("1");
        single.add("2");
        single.add("3");
        single.add("4");

        single.insert(0, "0");

        Assert.assertEquals(5, single.getSize());

        single.insert(5, "5");

        Assert.assertEquals(6, single.getSize());
    }

    @Test
    public void testDelete() {
        SingleSentryLinkedList single = new SingleSentryLinkedList();
        single.add("1");
        single.add("2");
        single.add("3");

        single.delete();
        Assert.assertEquals(2, single.getSize());

        single.delete();
        Assert.assertEquals(1, single.getSize());

        single.delete();
        Assert.assertEquals(0, single.getSize());
    }

    @Test
    public void testReverse() {
        SingleSentryLinkedList single = new SingleSentryLinkedList();
        single.add("1");

        single.reverse();
        single.printLinkedList();
        single.clear();

        // -------------------------------------------

        single.add("1");
        single.add("2");

        single.reverse();
        single.printLinkedList();
        single.clear();

        // ------------------------------------------

        single.add("1");
        single.add("2");
        single.add("3");

        single.reverse();
        single.printLinkedList();
        single.clear();

        // ------------------------------------------

        single.add("1");
        single.add("2");
        single.add("3");
        single.add("4");

        single.reverse();
        single.printLinkedList();
        single.clear();
    }
}
