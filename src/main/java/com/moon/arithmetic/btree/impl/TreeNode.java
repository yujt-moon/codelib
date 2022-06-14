package com.moon.arithmetic.btree.impl;

/**
 * 树的结点
 *
 * @author yujiangtao
 * @date 2018/6/13 15:00
 */
public class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    public TreeNode(int data) {
        this.data = data;
        left = null;
        right = null;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "data=" + data +
                '}';
    }
}
