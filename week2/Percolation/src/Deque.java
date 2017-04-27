import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author sealyu on 25/04/2017.
 */
public class Deque<Item> implements Iterable<Item> {
    public Deque(){

    }
    public boolean isEmpty(){

    }
    public int size(){

    }
    public void addFirst(Item item){
        if(null == item){
            throw new NullPointerException("Can't add a null item!");
        }

    }
    public void addLast(Item item){
        if(null == item){
            throw new NullPointerException("Can't add a null item!");
        }
    }
    public Item removeFirst(){
        if(this.isEmpty()){
            throw new NoSuchElementException("Queue is empty, you can't remove element from an empty queue!");
        }

    }
    public Item removeLast(){
        if(this.isEmpty()){
            throw new NoSuchElementException("Queue is empty, you can't remove element from an empty queue!");
        }

    }
    public Iterator<Item> iterator(){

    }
    public static void main(String[] args){

    }
}