package com.moon.arithmetic.btree.bookcode;

import org.junit.Before;
import org.junit.Test;

/**
 * @author yujiangtao
 * @date 2018/6/30 15:59
 */
public class TreeTest {

    Tree tree = null;

    @Before
    public void before() {
        tree = new Tree();

        // init binary tree
        tree.insert(50, 1.5);
        tree.insert(25, 1.7);
        tree.insert(75, 1.9);
        tree.insert(15, 1.6);
        tree.insert(35, 1.55);
        tree.insert(65, 1.3);
        tree.insert(85, 1.8);
    }

    @Test
    public void testInOrder() {
        tree.inOrder(tree.getRoot());
    }

    @Test
    public void testPreOrder() {
        tree.preOrder(tree.getRoot());
    }

    @Test
    public void testPostOrder() {
        tree.postOrder(tree.getRoot());
    }
}
