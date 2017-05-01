import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author sealyu on 25/04/2017.
 */
public class Permutation {
    public static void main(String[] args) {
        int size = StdIn.readInt();
        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        while (!StdIn.isEmpty())
        {
            rq.enqueue(StdIn.readString());
        }
        for (int i = 1; i <= size; i++)
        {
            StdOut.println(rq.dequeue());
        }
    }
}
