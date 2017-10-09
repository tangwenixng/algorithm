package com.haiboyu.algorithm.binarytree;

/**
 * This class describes a node of the binary tree
 * @author Haibo Yu on 10/08/2017.
 */
public class BinaryTreeNode {
    private int value;
    private BinaryTreeNode leftChild;
    private BinaryTreeNode rightChild;

    public BinaryTreeNode(int value) {
        this.value = value;
    }

    public BinaryTreeNode(int value, BinaryTreeNode leftChild, BinaryTreeNode rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BinaryTreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryTreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryTreeNode rightChild) {
        this.rightChild = rightChild;
    }
}
