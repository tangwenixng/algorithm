package com.haiboyu.algorithm.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * This class includes all linked list related algorithms
 * @author Haibo Yu on 09/27/2017.
 */
public class LinkedListAlgorithms {

    public static void main(String[] args) {
        LinkedListAlgorithms lla = new LinkedListAlgorithms();
        Node last = new Node(5,null);
        Node forthNode = new Node(4,last);
        Node thirdNode = new Node(3,forthNode);
        Node secondNode = new Node(2,thirdNode);
        Node headNodeBefore = new Node(1,secondNode);
        printList(headNodeBefore);
        //Node headNodeAfter = lla.deleteNodeInO1TimeComplexity(headNodeBefore,last);
        //Node headNodeAfter = lla.transposeLinkedList(headNodeBefore);
        //Node headNodeAfter = lla.getLastKNodeOfTheLinkedList(headNodeBefore,4);
        //Node headNodeAfter = lla.getMiddleNodeOfTheLinkedList(headNodeBefore);
        //printNode(headNodeAfter);
        //printList(headNodeAfter);
        last.setNext(thirdNode);
        //boolean hasCircle = lla.checkIfLinkedListHasCircle(headNodeBefore);
        //System.out.println(hasCircle);
        Node entryNode = lla.getCircleEntryNodeOfTheLinkedListWithCircle1(headNodeBefore);
        printNode(entryNode);
    }

    private static Node prepareSampleLinkedList(){
        Node last = new Node(5,null);
        Node forthNode = new Node(4,last);
        Node thirdNode = new Node(3,forthNode);
        Node secondNode = new Node(2,thirdNode);
        Node firstNode = new Node(1,secondNode);
        return firstNode;
    }

    public static void printList(Node headNode) {
        while (headNode != null) {
            System.out.print(headNode.getValue() + "->");
            headNode = headNode.getNext();
        }
        System.out.println("null");
    }

    public static void printNode(Node headNode) {
        System.out.print(headNode.getValue());
    }

    /**
     * Given a linked list, delete the node in O(1) time complexity.
     * @param headNodeBeforeDeletingNode The source head node of the linked list
     * @param nodeToBeDeleted The node need to be deleted.
     * @return The head node of the linked list after deleting the node.
     */
    public Node deleteNodeInO1TimeComplexity(Node headNodeBeforeDeletingNode, Node nodeToBeDeleted) {
        if(null == headNodeBeforeDeletingNode || null == nodeToBeDeleted){
            System.out.println("The source linked list or the node to be deleted is null, will do nothing!");
            return headNodeBeforeDeletingNode;
        }

        if(null == nodeToBeDeleted.getNext()){
            //Note: Here if the node need to be removed is the last node of the list, we will
            //not be able to delete it in O(1) time complexity, it's gonna to be O(N).
            Node tempNode = headNodeBeforeDeletingNode;
            while(tempNode.getNext() != null){
                if(tempNode.getNext().equals(nodeToBeDeleted)){
                    tempNode.setNext(null);
                    return headNodeBeforeDeletingNode;
                }
                tempNode = tempNode.getNext();
            }
        }else{
            Node nextNode = nodeToBeDeleted.getNext();
            nodeToBeDeleted.setValue(nextNode.getValue());
            nodeToBeDeleted.setNext(nextNode.getNext());
        }
        return headNodeBeforeDeletingNode;
    }

    /**
     * Transpose the linked list to a reverse order.
     * @param headNodeOfSourceList The head node of the source linked list
     * @return The head node of the transposed linked list.
     */
    public Node transposeLinkedList(Node headNodeOfSourceList){
        if(null == headNodeOfSourceList || headNodeOfSourceList.getNext() == null){
            System.out.println("The source linked list is null or only has 1 element, will do nothing!");
            return headNodeOfSourceList;
        }
        Node pre = null;
        Node next = null;
        Node cur = headNodeOfSourceList;
        while(cur != null){
            next = cur.getNext();
            cur.setNext(pre);
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * Given a linked list and a int k(between 1 and length of list), get the last K node.
     * @param headNodeOfSourceList
     * @param k The int k(between 1 and length of list)
     * @return The last k node
     */
    public Node getLastKNodeOfTheLinkedList(Node headNodeOfSourceList, int k){
        Node position1 = headNodeOfSourceList;
        Node position2 = headNodeOfSourceList;
        int j = k;
        //First, move position2 to the k-th element of the list.
        while(j > 1){
            position2 = position2.getNext();
            j--;
        }

        //Then move both position1 and position2 forward, when position2 reach end,
        // position1 is the last k-th element.
        while(position2.getNext() != null){
            position1 = position1.getNext();
            position2 = position2.getNext();
        }

        return position1;
    }

    /**
     * Given a linked list, get the middle node of the list:
     * If the length is odd, return the middle; else return any of the middle 2 node.
     * @param headNodeOfSourceList
     * @return The middle node
     */
    public Node getMiddleNodeOfTheLinkedList(Node headNodeOfSourceList){
        Node position1 = headNodeOfSourceList;
        Node position2 = headNodeOfSourceList;

        //Then move both position1 and position2 forward, when position2 reach end,
        // position1 is the last k-th element.
        while(position2 != null && position2.getNext() != null){
            position1 = position1.getNext();
            position2 = position2.getNext().getNext();
        }

        return position1;
    }

    /**
     * Given a linked list, check if it has circle:
     * @param headNodeOfSourceList
     * @return The flag of if it has circle
     */
    public boolean checkIfLinkedListHasCircle(Node headNodeOfSourceList){
        Node position1 = headNodeOfSourceList;
        Node position2 = headNodeOfSourceList;

        //Then move both position1 and position2 forward, if position2 reach null means no circle,
        // else position2 will catch up with position1 when there is circle.
        while(position2 != null && position2.getNext() != null){
            position1 = position1.getNext();
            position2 = position2.getNext().getNext();
            if(position1.equals(position2)){
                return true;
            }
        }
        return false;
    }

    /**
     * Given a linked list, get the entry node of the circle if the linked list has circle.
     * Use an extra list
     * @param headNodeOfSourceList
     * @return The middle node
     */
    public Node getCircleEntryNodeOfTheLinkedListWithCircle1(Node headNodeOfSourceList){
        List<Node> nodes = new ArrayList<>();

        while(headNodeOfSourceList.getNext() != null){
            nodes.add(headNodeOfSourceList);
            headNodeOfSourceList = headNodeOfSourceList.getNext();
            if(nodes.contains(headNodeOfSourceList)){
                return headNodeOfSourceList;
            }
        }

        return null;
    }

    /**
     * Given a linked list, get the entry node of the circle if the linked list has circle.
     * Using 2 pointers
     * @param headNodeOfSourceList
     * @return The middle node
     */
    public Node getCircleEntryNodeOfTheLinkedListWithCircle2(Node headNodeOfSourceList){
        Node position1 = headNodeOfSourceList;
        Node position2 = headNodeOfSourceList;

        //Then move both position1 and position2 forward, when position2 reach end,
        // position1 is the last k-th element.
        while(position2 != null && position2.getNext() != null){
            position1 = position1.getNext();
            position2 = position2.getNext().getNext();
        }

        return position1;
    }

}
