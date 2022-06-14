package com.moon.arithmetic.binarysearchtree;

/**
 * @author yujiangtao
 * @date 2020/8/11 下午5:49
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历二叉搜索树～");
        binarySortTree.infixOrder(); // 1 3 5 7 9 10 12

    }
}

// 创建二叉排序树
class BinarySortTree {

    private Node root;

    // 查找要删除结点
    public Node search(int value) {
        if(root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    // 查找要删除结点的父结点
    public Node searchParent(int value) {
        if(root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    // 添加结点的方法
    public void add(Node node) {
        if(root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    // 中序遍历
    public void infixOrder() {
        if(root != null) {
            root.infixOrder();
        } else {
            System.out.println("empty tree");
        }
    }
}

// 创建 node 结点
class Node {
    int value;

    Node left;

    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    // 查找要删除的结点
    public Node search(int value) {
        if(value == this.value) { // 找到就是该结点
            return this;
        } else if(value < this.value) { // 如果找到的值小于当前结点
            // 如果左子结点为空
            if(this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if(this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    // 查找要删除结点的父结点
    public Node searchParent(int value) {
        // 如果当前结点就是要删除结点的父结点，就返回
        if((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        } else {
            // 如果查找的值小于当前值，并且左子结点不为空
            if(value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if(value > this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }

    // 添加节点
    // 递归的形式添加结点，注意需要满足二叉排序数的要求
    public void add(Node node) {
        if(node == null) {
            return;
        }

        // 判断传入结点的值和当前子树的根结点值的关系
        if(node.value < this.value) {
            if(this.left == null) {
                this.left = node;
            } else {
                // 递归的向左子树添加
                this.left.add(node);
            }
        } else {
            // 添加结点的值大于当前结点的值
            if(this.right == null) {
                this.right = node;
            } else {
                // 递归的向右子树添加
                this.right.add(node);
            }
        }
    }

    public void infixOrder() {
        if(this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right != null) {
            this.right.infixOrder();
        }
    }
}
