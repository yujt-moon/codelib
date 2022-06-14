package com.moon.arithmetic.btree.bookcode;

/**
 * @author yujiangtao
 * @date 2018/6/22 15:25
 */
public class TreeApp {

    public static void main(String[] args) {
        // make a tree
        Tree theTree = new Tree();

        // insert 3 nodes
        theTree.insert(50, 1.5);
        theTree.insert(25, 1.7);
        theTree.insert(75, 1.9);

        Node found = theTree.find(25);
        if(found != null) {
            System.out.println("Found the node with key 25");
        } else {
            System.out.println("Could not find the node with key 25");
        }
    }
}
