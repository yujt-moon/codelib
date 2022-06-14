package com.moon.arithmetic.skiplist;

/**
 * @author yujiangtao
 * @date 2021/3/15 下午10:58
 */
public class SkipListTest {
    public static void main(String[] args) {
        SkipList skipList = new SkipList();

        skipList.insert(3);
        skipList.insert(7);
        skipList.insert(5);

        skipList.printAll();
    }
}
