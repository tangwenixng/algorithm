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
        //sa.iterateBinaryTreeByLevel(rootNode);
        //System.out.println(sa.getBiggestDistanceBetweenNodeOfBinaryTree(rootNode));

        int[] preOrderArr = new int[]{19,8,9,1,4,5,12,10,11};
        int[] midOrderArr = new int[]{1,9,4,8,12,5,10,19,11};
        BinaryTreeNode rootNodeRe = sa.rebuildBinaryTree(preOrderArr,midOrderArr);
        sa.iterateBinaryTreeByLevel(rootNodeRe);
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

    /**
     * Get the total leaf node number of the binary tree
     * @param rootNode The root node of the binary tree
     * @return The total leaf node number
     */
    public int getLeafNodeNumberForBinaryTree(BinaryTreeNode rootNode){
        if(null == rootNode){
            return 0;
        }

        if(null == rootNode.getLeftChild() && null == rootNode.getRightChild()){
            return 1;
        }

        int totalLeft = getLeafNodeNumberForBinaryTree(rootNode.getLeftChild());
        int totalRight = getLeafNodeNumberForBinaryTree(rootNode.getRightChild());

        return totalLeft + totalRight;
    }


    /**
     * Given 2 binary tree, check if they are same structure.
     * @param rootNode1 The root node of the 1st binary tree
     * @param rootNode2 The root node of the 2nd binary tree
     * @return If they have same strcture
     */
    public boolean checkIfTwoBinaryTreeHaveSameStructure(BinaryTreeNode rootNode1,BinaryTreeNode rootNode2){
        if(null == rootNode1 && null == rootNode2){
            return true;
        }else if(null == rootNode1 || null == rootNode2){
            return false;
        }


        boolean leftEqual = checkIfTwoBinaryTreeHaveSameStructure(rootNode1.getLeftChild(), rootNode2.getLeftChild());
        boolean rightEqual = checkIfTwoBinaryTreeHaveSameStructure(rootNode1.getRightChild(),rootNode2.getRightChild());

        return (leftEqual && rightEqual);
    }

    /**
     * Given a binary tree, check if it is AVL tree.
     * @param rootNode The root node of the binary tree
     * @return If the tree is AVL tree
     */
    public boolean checkIfAVLBinaryTree(BinaryTreeNode rootNode){
        if(null == rootNode){
            return true;
        }

        boolean leftAVL = checkIfAVLBinaryTree(rootNode.getLeftChild());
        boolean rightAVL = checkIfAVLBinaryTree(rootNode.getRightChild());

        int leftDepth = this.getDepthOfBinaryTree(rootNode.getLeftChild());
        int rightDepth = this.getDepthOfBinaryTree(rootNode.getRightChild());

        return (leftAVL && rightAVL && (Math.abs(leftDepth - rightDepth)<=1));
    }

    /**
     * Given a binary tree, get it's mirror tree.
     * @param rootNode The root node of the binary tree
     * @return The mirror tree
     */
    public BinaryTreeNode getMirrorBinaryTree(BinaryTreeNode rootNode){
        if(null == rootNode){
            return null;
        }

        BinaryTreeNode leftMirror = getMirrorBinaryTree(rootNode.getLeftChild());
        BinaryTreeNode rightMirror = getMirrorBinaryTree(rootNode.getRightChild());

        rootNode.setLeftChild(rightMirror);
        rootNode.setRightChild(leftMirror);

        return rootNode;
    }

    /**
     * Given a binary tree and 2 node, get their lowest common node.
     * @param rootNode The root node of the binary tree
     * @return The mirror tree
     */
    public BinaryTreeNode getLowestCommonNodeOfTwoNode(BinaryTreeNode rootNode, BinaryTreeNode node1, BinaryTreeNode node2){
        if(null == rootNode || null == node1 || null == node2){
            return null;
        }

        if(findNodeInTheTree(rootNode.getLeftChild(),node1)){
            //If node1 and node2 are found separately in left and right tree,then rootNode is the lowest common node.
            if(findNodeInTheTree(rootNode.getRightChild(),node2)){
                return rootNode;
            }else{
                return getLowestCommonNodeOfTwoNode(rootNode.getLeftChild(),node1,node2);
            }
        }else{
            if(findNodeInTheTree(rootNode.getLeftChild(),node2)){
                return rootNode;
            }else{
                return getLowestCommonNodeOfTwoNode(rootNode.getRightChild(),node1,node2);
            }
        }
    }

    /**
     * Check if the node is in the tree
     * @param rootNode The root node of the tree
     * @param node The node
     * @return If the tree has this node
     */
    private boolean findNodeInTheTree(BinaryTreeNode rootNode, BinaryTreeNode node){
        if(null == rootNode){
            return false;
        }
        if(rootNode.equals(node)){
            return true;
        }
        boolean findInLeft = findNodeInTheTree(rootNode.getLeftChild(),node);
        boolean findInRight = findNodeInTheTree(rootNode.getRightChild(),node);
        return findInLeft||findInRight;
    }

    /**
     * Get the biggest distance between nodes of binary tree
     * @param rootNode The root node of the binary tree
     * @return The distance
     */
    private int getBiggestDistanceBetweenNodeOfBinaryTree(BinaryTreeNode rootNode){
        if(null == rootNode){
            return 0;
        }

        int disLeft = getBiggestDistanceBetweenNodeOfBinaryTree(rootNode.getLeftChild());
        int disRight = getBiggestDistanceBetweenNodeOfBinaryTree(rootNode.getRightChild());

        int maxLeft = 0, maxRight = 0;
        if(null != rootNode.getLeftChild()){
            maxLeft = this.getDepthOfBinaryTree(rootNode.getLeftChild())+1;
        }
        if(null != rootNode.getRightChild()){
            maxRight = this.getDepthOfBinaryTree(rootNode.getRightChild())+1;
        }

        return Math.max(Math.max(disLeft,disRight),maxLeft+maxRight);
    }

    /**
     * Rebuild the binary tree based on the pre-order and middle-order output of the tree.
     * @param preOrderArr The pre-order array output
     * @param midOrderArr The middle-order array output
     * @return The head node of the binary tree
     */
    public BinaryTreeNode rebuildBinaryTree(int[] preOrderArr, int[] midOrderArr){
        if (preOrderArr == null || midOrderArr == null || preOrderArr.length != midOrderArr.length || preOrderArr.length < 1 || midOrderArr.length < 1) {
            return null;
        }

        return rebuildBinaryTree(preOrderArr, 0, preOrderArr.length - 1, midOrderArr, 0, midOrderArr.length -1);
    }

    /**
     * Recursively build the tree
     * @param preOrderArr The pre order array
     * @param preStart The start index of pre order array
     * @param preEnd The end index of the pre order array
     * @param midOrderArr The middle order array
     * @param midStart The start index of middle order array
     * @param midEnd The end index of middle order array
     * @return The root node of the sub tree after building.
     */
    public BinaryTreeNode rebuildBinaryTree(int[] preOrderArr, int preStart, int preEnd, int[] midOrderArr, int midStart, int midEnd){
        if (preStart > preEnd) {
            return null;
        }

        // Get current root node
        int rootValue = preOrderArr[preStart];
        //Get the index of current root node in the midOrderArray
        int indexInMid = midStart;
        while (indexInMid <= midEnd && midOrderArr[indexInMid] != rootValue) {
            indexInMid++;
        }

        // Create the current root node
        BinaryTreeNode rootNode = new BinaryTreeNode(rootValue);

        // Rebuild the left sub tree
        BinaryTreeNode leftChild = rebuildBinaryTree(preOrderArr, preStart + 1,
                preStart + indexInMid - midStart,
                midOrderArr, midStart, indexInMid - 1);
        //Rebuild the right sub tree
        BinaryTreeNode rightChild = rebuildBinaryTree(preOrderArr, preStart + indexInMid - midStart + 1,
                preEnd, midOrderArr, indexInMid + 1, midEnd);

        rootNode.setLeftChild(leftChild);
        rootNode.setRightChild(rightChild);
        return rootNode;
    }

    /**
     * Given a binary tree, check if it's complete binary tree.
     * @param rootNode The root node of the tree
     * @return The flag of if it is complete binary tree
     */
    public boolean checkIfCompleteBinaryTree(BinaryTreeNode rootNode){
        if(null == rootNode){
            return false;
        }

        Queue<BinaryTreeNode> nodes = new LinkedList<>();
        nodes.offer(rootNode);
        boolean startHavingEmptyChild = false;
        while(!nodes.isEmpty()){
            BinaryTreeNode curNode = nodes.poll();
            System.out.println("Pop Node:"+curNode.getValue());
            //If already there's node which has empty child, then this is not complete tree.
            if(startHavingEmptyChild
                    && (null == curNode.getLeftChild() || null == curNode.getRightChild())){
                return false;
            }

            if(null != curNode.getLeftChild() && null != curNode.getRightChild()){
                nodes.offer(curNode.getLeftChild());
                nodes.offer(curNode.getRightChild());
            }else if(null != curNode.getLeftChild() && null == curNode.getRightChild()){
                startHavingEmptyChild = true;
                nodes.offer(curNode.getLeftChild());
            }else if(null == curNode.getLeftChild() && null != curNode.getRightChild()){
                return false;
            }else{
                startHavingEmptyChild = true;
            }
        }
        return true;
    }

}
