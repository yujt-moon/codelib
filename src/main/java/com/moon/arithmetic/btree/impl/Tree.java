package com.moon.arithmetic.btree.impl;

/**
 * 树结构
 *
 * @author yujiangtao
 * @date 2020/8/12 上午10:31
 */
public class Tree {

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

    /**
     * 查询某个值所在的结点
     *
     * @param value
     * @return
     */
    public TreeNode find(int value) {
        TreeNode current = root;
        while(true) {
            if(current == null) {
                return null;
            }
            if(current.data == value) {
                return current;
            } else if(value < current.data) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
    }

    /**
     * 查找要删除结点的父结点
     *
     * @param value
     * @return
     */
    public TreeNode findParent(int value) {
        TreeNode parent = null;
        TreeNode current = root;
        while(true) {
            if(current == null) {
                return null;
            }
            if(value == current.data) {
                return parent;
            } else if(value < current.data) {
                parent = current;
                current = current.left;
            } else {
                parent = current;
                current = current.right;
            }
        }
    }

    /**
     * 删除指定结点
     * @param value
     */
    public void delete(int value) {
        TreeNode treeNode = find(value);
        if(treeNode == null) {
            throw new RuntimeException("当前结点不存在！");
        }
        // 查询要删除结点的父结点
        TreeNode parent = findParent(value);
        // 删除的结点是叶子结点
        if(treeNode.left == null && treeNode.right == null) {
            // 如果当前要删除的结点是根结点
            if(parent == null) {
                root = null;
                return;
            }
            // 如果当前结点是父结点的左子结点
            if(parent.left.data == value) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        // 要删除的结点只有单结点（只存在左子结点）
        else if(treeNode.left != null && treeNode.right == null) {
            TreeNode rightNode = treeNode.left;
            // 找到当前结点下的最大的结点
            while(true) {
                if(rightNode.right != null) {
                    rightNode = rightNode.right;
                }
                break;
            }
            if(parent == null) {
                root = rightNode;
            } else {
                parent.left = rightNode;
            }
        }
        // 要删除的结点只有单结点（只存在右子结点）
        else if(treeNode.right != null && treeNode.left == null) {
            TreeNode leftNode = treeNode.right;
            // 找到当前结点下的最大的结点
            while(true) {
                if(leftNode.left != null) {
                    leftNode = leftNode.left;
                }
                break;
            }
            if(parent == null) {
                root = leftNode;
            } else {
                parent.right = leftNode;
            }
        }
        // 要删除的结点存在左右子结点
        else {
            TreeNode leftNode = treeNode.left;
            TreeNode rightNode = treeNode.right;
            // 将左侧的树结点，挂到右侧树上
            TreeNode temp = rightNode;
            while(true) {
                if(temp.left == null) {
                    temp.left = leftNode;
                    break;
                } else {
                    temp = temp.left;
                }
            }
            if(parent == null) {
                root = rightNode;
            } else if(parent.left.data == value) {
                parent.left = rightNode;
            } else {
                parent.right = rightNode;
            }
        }
    }

    public void inOrder() {
        inorder(root);
        System.out.println();
    }

    public void preOrder() {
        preorder(root);
        System.out.println();
    }

    public void postOrder() {
        postorder(root);
        System.out.println();
    }

    private void inorder(TreeNode current) {
        if(current != null) {
            inorder(current.left);
            System.out.print(current.data + "\t");
            inorder(current.right);
        }
    }

    private void preorder(TreeNode current) {
        if(current != null) {
            System.out.print(current.data + "\t");
            preorder(current.left);
            preorder(current.right);
        }
    }

    private void postorder(TreeNode current) {
        if(current != null) {
            postorder(current.left);
            postorder(current.right);
            System.out.print(current.data + "\t");
        }
    }
}
