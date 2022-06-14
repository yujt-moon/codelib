package com.moon.arithmetic.avl;

/**
 * @author yujiangtao
 * @date 2020/8/12 下午4:56
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        // int[] arr = {4, 3, 6, 5, 7, 8}; // 左旋转数据
        // int[] arr = {10, 12, 8, 9, 7, 6}; // 右旋转数据
        int[] arr = {10, 11, 7, 6, 8, 9};
        // int[] arr = {2, 1, 6, 5, 7, 3};
        // 创建一个 AVLTree 对象
        AVLTree avlTree = new AVLTree();
        // 添加结点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        // 遍历
        System.out.println("中序遍历");
        avlTree.infixOrder();

        System.out.println("在做平衡处理～～");
        System.out.println("树的高度：" + avlTree.getRoot().height());
        System.out.println("左子树的高度：" + avlTree.getRoot().leftHeight());
        System.out.println("右子树的高度：" + avlTree.getRoot().rightHeight());
    }
}

class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

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

    // 返回左子树的高度
    public int leftHeight() {
        if(left == null) {
            return 0;
        }
        return left.height();
    }

    // 返回右子树的高度
    public int rightHeight() {
        if(right == null) {
            return 0;
        }
        return right.height();
    }


    // 返回当前结点的高度，以该结点的树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    // 左旋转方法
    private void leftRotate() {
        // 创建新的结点，以当前根结点的值
        Node newNode = new Node(value);
        // 把新的结点的左子树设置成当前结点的左子树
        newNode.left = left;
        // 把新的结点的右子树设置成当前结点的右子树的左子树
        newNode.right = right.left;
        // 把当前结点的值替换成右子结点的值
        value = right.value;
        // 把当前结点的右子树设置成右子树的右子树
        right = right.right;
        // 把当前结点的左子结点，设置成新的结点
        left = newNode;
    }

    // 右旋转方法
    private void rightRotate() {
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
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

        // 当添加完一个结点后，如果 （右子树的高度-左子树的高度） > 1，左旋转
        if(rightHeight() - leftHeight() > 1) {
            // 如果它的右子树的左子树的高度大于它的右子树的右子树的高度
            // 先对右子结点进行右旋转，
            // 再对当前结点进行左旋转
            if(right != null && right.leftHeight() > right.rightHeight()) {
                right.rightRotate();
            }
            leftRotate();
            return;
        }

        // 当添加完一个结点后，如果 （左子树的高度-右子树的高度） > 1，右旋转
        if(leftHeight() - rightHeight() > 1) {
            // 如果它的左子树的右子树的高度大于它的左子树的高度
            if(left != null && left.rightHeight() > left.leftHeight()) {
                // 先对当前结点的左结点（左子树）->左旋转
                left.leftRotate();
            }
            // 再对当前结点进行右旋转
            rightRotate();
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
