package com.haiboyu.algorithm.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Haibo Yu on 10/08/2017.
 */
public class BinaryTreeAlgorithms {

    public static void main(String[] args) {

        BinaryTreeNode rootNode = prepareDummyBinaryTree();
        BinaryTreeAlgorithms sa = new BinaryTreeAlgorithms();
        //System.out.println(sa.getNodeCountOfBinaryTree(rootNode));
        sa.iterateBinaryTreeByLevel(rootNode);
        System.out.println(sa.getNodeNumberForKthLevel(rootNode,4));
    }

    private static BinaryTreeNode prepareDummyBinaryTree(){
        BinaryTreeNode node1 = new BinaryTreeNode(12);
        BinaryTreeNode node2 = new BinaryTreeNode(10);
        BinaryTreeNode node3 = new BinaryTreeNode(1);
        BinaryTreeNode node4 = new BinaryTreeNode(4);
        BinaryTreeNode node5 = new BinaryTreeNode(9,node3,node4);
        BinaryTreeNode node6 = new BinaryTreeNode(5,node1,node2);
        BinaryTreeNode node7 = new BinaryTreeNode(8,node5,node6);
        BinaryTreeNode node8 = new BinaryTreeNode(11);
        BinaryTreeNode rootNode = new BinaryTreeNode(19,node7,node8);
        return rootNode;
    }

    /**
     * Given a root node of a binary tree, get the node count
     * @param rootNode The root node of the binary tree
     * @return The count of the nodes in the binary tree
     */
    public int getNodeCountOfBinaryTree(BinaryTreeNode rootNode){
        if(null == rootNode){
            return 0;
        }else{
            return getNodeCountOfBinaryTree(rootNode.getLeftChild()) + getNodeCountOfBinaryTree(rootNode.getRightChild()) + 1;
        }
    }

    /**
     * Given a root node of a binary tree, get the tree depth
     * @param rootNode The root node of the binary tree
     * @return The depth of the binary tree
     */
    public int getDepthOfBinaryTree(BinaryTreeNode rootNode){
        if(null == rootNode){
            return 0;
        }else{
            int leftSubTreeDepth = getDepthOfBinaryTree(rootNode.getLeftChild());
            int rightSubTreeDepth = getDepthOfBinaryTree(rootNode.getRightChild());
            return  (leftSubTreeDepth > rightSubTreeDepth )?(leftSubTreeDepth + 1):(rightSubTreeDepth + 1);
        }
    }

    /**
     * Given a root node of a binary tree, iterate the tree by every level
     * @param rootNode The root node of the binary tree
     */
    public void iterateBinaryTreeByLevel(BinaryTreeNode rootNode){
        if(null == rootNode){
            return ;
        }

        Queue<BinaryTreeNode> nodes = new LinkedList<>();
        nodes.offer(rootNode);
        while(!nodes.isEmpty()){
            BinaryTreeNode curNode = nodes.poll();
            System.out.println("Pop Node:"+curNode.getValue());
            if(null != curNode.getLeftChild()){
                nodes.offer(curNode.getLeftChild());
            }
            if(null != curNode.getRightChild()){
                nodes.offer(curNode.getRightChild());
            }
        }
    }

    /**
     * Convert a binary search tree into a doubly linked list, the requirement is not to create
     * new node, only change the pointers based on the existing node.
     * @param rootNode The root node of the binary search tree.
     * @return The head node of the doubly linked list.
     */
    public void convertBinaryTreeToDoublyLinkedList(BinaryTreeNode rootNode){
        if(null == rootNode){
            return;
        }

        if(null != rootNode.getLeftChild()){
            convertBinaryTreeToDoublyLinkedList(rootNode.getLeftChild());
            BinaryTreeNode biggestNode = findBiggestNodeInTheTree(rootNode.getLeftChild());
            biggestNode.setRightChild(rootNode);
            rootNode.setLeftChild(biggestNode);
        }
        if(null != rootNode.getRightChild()){
            convertBinaryTreeToDoublyLinkedList(rootNode.getRightChild());
            BinaryTreeNode smallestNode = findSmalledNodeInTheTree(rootNode.getRightChild());
            smallestNode.setLeftChild(rootNode);;
            rootNode.setRightChild(smallestNode);
        }
    }

    /**
     * Find the smallest node in the binary search tree
     * @param rootNode The root node
     * @return The smallest node in the binary search tree
     */
    private BinaryTreeNode findSmalledNodeInTheTree(BinaryTreeNode rootNode){
        while(rootNode.getLeftChild() != null){
            rootNode = rootNode.getLeftChild();
        }
        return rootNode;
    }

    /**
     * Find the biggest node in the binary search tree
     * @param rootNode The root node
     * @return The biggest node in the binary search tree
     */
    private BinaryTreeNode findBiggestNodeInTheTree(BinaryTreeNode rootNode){
        while(rootNode.getRightChild() != null){
            rootNode = rootNode.getRightChild();
        }
        return rootNode;
    }

    /**
     * Get the total node number of the kth level of the binary tree
     * @param rootNode The root node of the binary tree
     * @param k The level number
     * @return The total node number of the kth level.
     */
    public int getNodeNumberForKthLevel(BinaryTreeNode rootNode, int k){
        if(null == rootNode || k<1){
            return 0;
        }

        if(k ==1){
            return 1;
        }

        int totalLeft = getNodeNumberForKthLevel(rootNode.getLeftChild(),k-1);
        int totalRight = getNodeNumberForKthLevel(rootNode.getRightChild(),k-1);
        return totalLeft + totalRight;
    }

}
