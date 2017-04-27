import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author sealyu on 25/04/2017.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    public RandomizedQueue(){

    }
    public boolean isEmpty(){

    }
    public int size(){

    }
    public void enqueue(Item item){
        if(null == item){
            throw new NullPointerException("Can't add a null item!");
        }

    }
    public Item dequeue(){
        if(this.isEmpty()){
            throw new NoSuchElementException("Queue is empty, you can't remove element from an empty queue!");
        }
        int randomValue = StdRandom.uniform(10);
    }
    public Item sample(){
        if(this.isEmpty()){
            throw new NoSuchElementException("Queue is empty, you can't remove element from an empty queue!");
        }

    }
    public Iterator<Item> iterator(){

    }
    public static void main(String[] args){
        
    }
}