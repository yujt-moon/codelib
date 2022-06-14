package com.moon.arithmetic.btree.impl;

import org.junit.Test;

/**
 * @author yujiangtao
 * @date 2020/8/12 上午10:55
 */
public class TestTree {

    @Test
    public void testAdd() {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        Tree tree = new Tree();
        for (int i = 0; i < arr.length; i++) {
            tree.add(arr[i]);
        }
        tree.inOrder();
        tree.preOrder();
        tree.postOrder();

        System.out.println("find(9): " + tree.find(9));

        System.out.println("findParent(7): " + tree.findParent(7));
        System.out.println("findParent(3): " + tree.findParent(3));
        System.out.println("findParent(9): " + tree.findParent(9));

        tree.delete(1);
        tree.inOrder();
    }

    @Test
    public void testAclAdd() {
        int[] arr = {4, 3, 6, 5, 7, 8}; // 左旋转数据
        AVLTree tree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            tree.add(arr[i]);
        }

        System.out.println(tree.length(tree.getRoot().left));
    }
}
