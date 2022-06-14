package com.moon.arithmetic.btree.bookcode;

/**
 * @author yujiangtao
 * @date 2018/6/22 15:15
 */
public class Tree {

    // the only data field in Tree
    private Node root;

    // find node with given key
    public Node find(int key) {
        // assumes non-empty tree
        // start at root
        Node current = root;
        // while no match,
        while(current.iData != key) {
            // go left?
            if(key < current.iData) {
               current = current.leftChild;
            } else { // or go right?
                current = current.rightChild;
            }
            // if no child,
            if(current == null) {
                // didn't find it
                return null;
            }
        }
        // find it
        return current;
    }

    public void insert(int id, double dd) {
        // make new node
        Node newNode = new Node();
        // insert data
        newNode.iData = id;
        newNode.fData = dd;
        // no node in root
        if(root == null) {
            root = newNode;
        } else { // root occupied
            // start at root
            Node current = root;
            Node parent;
            // (exists internally)
            while(true) {
                parent = current;
                // go left?
                if(id < current.iData) {
                    current = current.leftChild;
                    // if end of the line
                    // insert on left
                    if(current == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else { // or go right?
                    current = current.rightChild;
                    // if end of the line
                    // insert on right
                    if(current == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    // 中序遍历
    public void inOrder(Node localRoot) {
       if(localRoot != null) {
           inOrder(localRoot.leftChild);
           System.out.print(localRoot.iData + " ");
           inOrder(localRoot.rightChild);
       }
    }

    // 前序遍历
    public void preOrder(Node localRoot) {
        if(localRoot != null) {
            System.out.print(localRoot.iData + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }

    // 后序遍历
    public void postOrder(Node localRoot) {
        if(localRoot != null) {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.iData + " ");
        }
    }

    // returns node with minimum key value
    public Node minimum() {
        Node current, last = null;
        // start at root
        current = root;
        // until the bottom
        while(current != null) {
            // remember node
            last = current;
            // go to left child
            current = current.leftChild;
        }
        return last;
    }

    // delete node with given key
    public boolean delete(int key) {
        // (assumes non-empty list)
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;

        // search for node
        while(current.iData != key) {
            parent = current;
            // go left?
            if(key < current.iData) {
                isLeftChild = true;
                current = current.leftChild;
            } else { // or go right?
                isLeftChild = false;
                current = current.rightChild;
            }

            // end of the line,
            if(current == null) {
                // didn't find it
                return false;
            }
        }

        // delete() continued...
        // if no children, simple delete it
        if(current.leftChild == null && current.rightChild == null) {
            // if root,
            if(current == root) {
                // tree is empty
                root = null;
            } else if(isLeftChild){
                // disconnect from parent
                parent.leftChild = null;
            }
        } else if(current.rightChild == null) { // if no right child, replace with left subtree
            if(current == root) {
                root = current.leftChild;
            } else if(isLeftChild) { // left child of parent
                parent.leftChild = current.leftChild;
            } else { // right child of parent
                parent.rightChild = current.leftChild;
            }
        } else if (current.leftChild == null) { // if no left child, replace with right subtree
            if(current == root) {
                root = current.rightChild;
            } else if(isLeftChild) { // left child of parent
                parent.leftChild = current.rightChild;
            } else { // right child of parent
                parent.rightChild = current.rightChild;
            }
        }
        return false;
    }

    // returns node with next-highest value after delNode
    // goes to right child, then right child's left descendants
    private Node getSuccessor(Node delNode) {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightChild;  // go to right child
        // util no more
        while(current != null) {
            successorParent = successor;
            successor = current;
            // go to left child
            current = current.leftChild;
        }

        // if successor not right child, make connections
        if(successor != delNode.rightChild) {
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }
        return successor;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
