package com.moon.arithmetic.linkedlist;

import com.moon.arithmetic.linkedlist.LRULinkedList;
import org.junit.Test;

/**
 * @author yujiangtao
 * @date 2020/9/25 下午2:43
 */
public class LRULinkedListTest {

    @Test
    public void testAdd1() {
        LRULinkedList<String, String> cache = new LRULinkedList<>(5);
        cache.add("5", "5.0");
        cache.add("4", "4.0");
        cache.add("3", "3.0");
        cache.add("2", "2.0");
        cache.add("1", "1.0");
        cache.printAllNode();

        cache.add("3", "3.1");
        cache.printAllNode();
    }

    @Test
    public void testAdd2() {
        LRULinkedList<String, String> cache = new LRULinkedList<>(5);
        cache.add("4", "4.0");
        cache.add("3", "3.0");
        cache.add("2", "2.0");
        cache.add("1", "1.0");
        cache.printAllNode();

        cache.add("3", "3.1");
        cache.printAllNode();
    }

    @Test
    public void testAdd3() {
        LRULinkedList<String, String> cache = new LRULinkedList<>(5);
        cache.add("4", "4.0");
        cache.add("3", "3.0");
        cache.add("2", "2.0");
        cache.add("1", "1.0");
        cache.printAllNode();

        cache.add("1", "1.1");
        cache.printAllNode();
    }

    @Test
    public void testAdd4() {
        LRULinkedList<String, String> cache = new LRULinkedList<>(5);
        cache.add("5", "5.0");
        cache.add("4", "4.0");
        cache.add("3", "3.0");
        cache.add("2", "2.0");
        cache.add("1", "1.0");
        cache.printAllNode();

        cache.add("6", "6.0");
        cache.add("7", "7.0");
        cache.printAllNode();
    }
}
