package com.moon.arithmetic.btree.impl;

/**
 * 二叉树
 * @author yujiangtao
 * @date 2019/3/6 16:39
 */
public class BinaryTree<T> {

    /**
     * 二叉树根节点
     */
    private BinNode<T> root;

    public void add(T t) {
        /**
         * 若还无根节点
         */
        if(root == null) {
            root = new BinNode<T>(t);
        } else {
            if(t instanceof Number) {
                
            }
            // TODO
            else {

            }
        }
    }


    /**
     * 二叉树节点
     * @param <T>
     */
    private static class BinNode<T> implements Comparable<T> {
        /**
         * 左节点
         */
        public BinNode<T> leftNode;

        /**
         * 节点数据
         */
        public T data;

        /**
         * 右节点
         */
        public BinNode<T> rightNode;

        public BinNode(BinNode<T> leftNode, T data, BinNode<T> rightNode) {
            this.leftNode = leftNode;
            this.data = data;
            this.rightNode = rightNode;
        }

        public BinNode(T data) {
            this(null, data, null);
        }

        public int compareTo(T o) {
            return 0;
        }
    }
}
