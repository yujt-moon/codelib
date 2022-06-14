package com.moon.arithmetic.linkedlist;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author yujiangtao
 * @date 2020/9/26 下午10:43
 */
public class LRUArrayTest {

    @Test
    public void testAdd1() {
        LRUArray<String, String> lruArray = new LRUArray<>(5);
        lruArray.add("1", "1.0");
        Assert.assertEquals(0, lruArray.getIndex());

        lruArray.add("2", "2.0");
        lruArray.add("3", "3.0");
        lruArray.add("4", "4.0");
        lruArray.add("5", "5.0");

        lruArray.printAllNodes();
    }

    @Test
    public void testAdd2() {
        LRUArray<String, String> lruArray = new LRUArray<>(5);
        lruArray.add("1", "1.0");
        Assert.assertEquals(0, lruArray.getIndex());

        lruArray.add("2", "2.0");
        lruArray.add("3", "3.0");
        lruArray.add("4", "4.0");

        lruArray.add("3", "3.1");

        lruArray.printAllNodes();
    }

    @Test
    public void testAdd3() {
        LRUArray<String, String> lruArray = new LRUArray<>(5);
        lruArray.add("1", "1.0");
        Assert.assertEquals(0, lruArray.getIndex());

        lruArray.add("2", "2.0");
        lruArray.add("3", "3.0");
        lruArray.add("4", "4.0");
        lruArray.add("5", "5.0");

        lruArray.add("3", "3.1");
        lruArray.add("1", "1.1");
        lruArray.add("1", "1.2");
        lruArray.add("6", "6.0");

        lruArray.printAllNodes();
    }
}
