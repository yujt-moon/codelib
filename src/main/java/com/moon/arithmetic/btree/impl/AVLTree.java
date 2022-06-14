package com.moon.arithmetic.btree.impl;

/**
 * @author yujiangtao
 * @date 2020/8/13 下午5:24
 */
public class AVLTree {
    /**
     * 根结点
     */
    private TreeNode root;

    /**
     * 新增结点
     * @param value
     */
    public void add(int value) {
        if(root == null) {
            root = new TreeNode(value);
        } else {
            TreeNode current = root;
            while(true) {
                // 如果当前的值小于当前结点的值
                if(value < current.data) {
                    // 当前左子结点为空，直接赋值
                    if(current.left == null) {
                        current.left = new TreeNode(value);
                        return;
                    } else {
                        // 当前值赋值为左子结点的值，继续循环
                        current = current.left;
                    }
                } else {
                    // 当前右子结点为空，直接赋值
                    if(current.right == null) {
                        current.right = new TreeNode(value);
                        return;
                    } else {
                        // 当前值赋值为右子结点的值，继续循环
                        current = current.right;
                    }
                }
            }
        }
    }


    public void leftRotate() {}

    /**
     * 树的高度
     * @param node
     * @return
     */
    public int length(TreeNode node) {
        return Math.max(node.left != null ? length(node.left) : 0,
                node.right != null ? length(node.right) : 0) + 1;
    }

    /**
     * 左子树的高度
     * @param node
     * @return
     */
    public int leftLength(TreeNode node) {
        return length(node.left);
    }

    /**
     * 右子树的高度
     * @param node
     * @return
     */
    public int rightLegth(TreeNode node) {
        return length(node.right);
    }

    public TreeNode getRoot() {
        return root;
    }

    public void inOrder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(TreeNode current) {
        if(current != null) {
            inorder(current.left);
            System.out.print(current.data + "\t");
            inorder(current.right);
        }
    }

}
