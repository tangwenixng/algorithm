package com.haiboyu.algorithm.linkedlist;

/**
 * Node used for Doubly linked list
 * @author Haibo Yu on 10/05/2017.
 */
public class DoublyNode {
    private int value;
    private DoublyNode previous;
    private DoublyNode next;

    public DoublyNode(int value, DoublyNode previous, DoublyNode next) {
        this.value = value;
        this.previous = previous;
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public DoublyNode getPrevious() {
        return previous;
    }

    public void setPrevious(DoublyNode previous) {
        this.previous = previous;
    }

    public DoublyNode getNext() {
        return next;
    }

    public void setNext(DoublyNode next) {
        this.next = next;
    }
}
