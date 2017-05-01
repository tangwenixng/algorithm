import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author sealyu on 25/04/2017.
 */
public class Deque<Item> implements Iterable<Item> {

    // size of the queue
    private int size;
    // first of queue
    private Node first;
    // last of queue
    private Node last;

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
        private Node previous;

        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }
    }


    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (null == item) {
            throw new NullPointerException("Can't add a null item!");
        }
        if (null != first) {
            Node oldFirst = first;
            first = new Node();
            first.setItem(item);
            first.setNext(oldFirst);
            first.setPrevious(null);
            oldFirst.setPrevious(first);
        } else {
            first = new Node();
            first.setItem(item);
            first.setNext(null);
            first.setPrevious(null);
            last = first;
        }
        size++;
    }
    public void addLast(Item item) {
        if (null == item) {
            throw new NullPointerException("Can't add a null item!");
        }
        if (null != last) {
            Node oldLast = last;
            last = new Node();
            last.setItem(item);
            last.setPrevious(oldLast);
            last.setNext(null);
            oldLast.setNext(last);
        } else {
            last = new Node();
            last.setItem(item);
            last.setPrevious(null);
            last.setNext(null);
            first = last;
        }
        size++;
    }
    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Queue is empty, you can't remove element from an empty queue!");
        }
        if (null != first) {
            Item nodeRemoved = first.getItem();
            Node second = first.getNext();
            if (null != second) {
                first = second;
                first.setPrevious(null);
            } else {
                first = null;
                last = null;
            }
            size--;
            return nodeRemoved;
        } else {
            return null;
        }
    }

    public Item removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Queue is empty, you can't remove element from an empty queue!");
        }
        if (null != last) {
            Item nodeRemoved = last.getItem();
            Node secondLast = last.getPrevious();
            if (null != secondLast) {
                last = secondLast;
                last.setNext(null);
            } else {
                last = null;
                first = null;
            }
            size--;
            return nodeRemoved;
        } else {
            return null;
        }
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.getItem();
            current = current.getNext();
            return item;
        }
    }

    public static void main(String[] args) {

    }
}