import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author sealyu on 25/04/2017.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    // The item array
    private Item[] items;
    // The size of the queue
    private int size;

    public RandomizedQueue() {
        items = (Item[]) new Object[1];
        size = 0;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void enqueue(Item item) {
        if (null == item) {
            throw new NullPointerException("Can't add a null item!");
        }
        if (items.length == size) {
            resize(items.length*2);
            items[size] = item;
            size++;
        } else {
            items[size] = item;
            size++;
        }
    }
    public Item dequeue() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Queue is empty, you can't remove element from an empty queue!");
        }
        int randomValue = StdRandom.uniform(size);
        Item returnItem = items[randomValue];
        if (size-1 == randomValue) {
            items[randomValue] = null;
        } else {
            items[randomValue] = items[size-1];
            items[size-1] = null;
        }
        if (size == items.length/4) {
            resize(items.length/2);
        }
        size--;
        return returnItem;
    }
    public Item sample() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Queue is empty, you can't remove element from an empty queue!");
        }
        return items[StdRandom.uniform(size)];
    }
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }

    private class RandomQueueIterator implements Iterator<Item>
    {
        private int i = 0;
        private int[] indices;

        public RandomQueueIterator()
        {
            indices = new int[size];
            for (int j = 0; j < indices.length; j++)
            {
                indices[j] = j;
            }
            StdRandom.shuffle(indices);
        }

        public boolean hasNext()
        {
            return i < size;
        }

        public Item next()
        {
            if (!hasNext()) throw new java.util.NoSuchElementException("No more items in iteration.");
            return items[indices[i++]];
        }

        public void remove()
        {
            throw new UnsupportedOperationException("remove() is not supported");
        }
    }

    private void resize(int capacity)
    {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    public static void main(String[] args) {

    }
}