/*****************************************************************************
 * Author:       Bryan Goodrich
 * Written:      2/14/2015
 * Last updated: 2/16/2015
 * 
 * Compilation:  javac RandomizedQueue.java
 * Execution:    java RandomizedQueue < input.txt
 * Dependencies: StdOut.java StdIn.java StdRandom.java
 * 
 * Queue like implementation with random access methods
 * 
 * % cat input.txt
 * A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
 * 
 * % java RandomizedQueue < input.txt
 * Inserted: A B C D E F G H I J K L M N O P Q R S T U V W X Y Z 
 * 1st Random Iteration: A N C H F W S Q I D K V Y E M Z X J O U L T G R P B 
 * 2nd Random Iteration: J B F I A M P O Y T S R E N H C U L D V K Q Z G X W 
 * Randomly Dequeue: Q H X R Y L C I T B O K F N A P Z E S U M G V W J D 
 * 
 ****************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Randomized Queue
 * 
 * Implements a queue or stack like structure with access methods that 
 * uniformly at random sample or remove from the data structure
 * 
 * The API here is designed by Robert Sedgewick and Kevin Wayne as part of
 * their online <i>Coursera</i> class.
 * 
 * For more resources, see the 
 * <a href="http://algs4.cs.princeton.edu/home/">Algorithms</a> homepage.
 * 
 * @author Bryan Goodrich
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    
    // Array maintaining the queue data
    private Item[] queue;
    
    // Number of items in queue
    private int N;

    /**
     * Initialize an empty queue of size 2
     */
    public RandomizedQueue() {
        queue = (Item[]) new Object[2];
        N = 0;
    }
    
    /**
     * Checks if queue is empty
     * @return true if if queue is empty; false otherwise
     */
    public boolean isEmpty() {
        return N == 0;
    }
    
    /**
     * Returns the number of items in the queue
     * @return the number of items in the queue
     */
    public int size() {
        return N;
    }
    
    /**
     * Add item to queue
     * @param item  an Item type item
     */
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        if (N == queue.length) resize(queue.length * 2);
        queue[N++] = item;
    }
    
    /**
     * Returns and removes (pop) a random item in queue
     * @return a random item from queue
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        exch(queue, StdRandom.uniform(N), N-1);
        Item item = queue[N-1];
        queue[N-1] = null;
        N--;
        
        if (N > 0 && N == queue.length / 4) resize(queue.length / 2);
        return item;
    }
    
    /**
     * Returns, but does not remove (peek), a random item in queue
     * @return a random item from queue
     */
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        return queue[StdRandom.uniform(N)];
    }
    
    /**
     * Return an iterator with an independent random order of items in queue
     * @return an iterator with an independent random order of items in queue
     */
    public Iterator<Item> iterator() { return new RandomIterator(); }
    
    // resize the array holding the queue elements
    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
    }
    
    // Exchange array items between the two indices
    private void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

     /**
     * An iterator over a random queue
     * 
     * This iterator creates a random copy of the underlying 
     * random queue and returns (dequeue) this copy. Thus, 
     * all iterators provide independent randomizations of
     * the queue. The remove method is not supported.
     * 
     */
    private class RandomIterator implements Iterator<Item> {
        
        // Index into array
        private int i;
        
        // An array to copy a random version of underlying queue
        private Item[] randomqueue;
        
        // Instantiate the Random Queue of size N with its own shuffle
        public RandomIterator() {
            int n;
            i = 0;
            randomqueue = (Item[]) new Object[N];
            
            for (int j = 0; j < N; j++) {
                randomqueue[j] = queue[j];
            }
            StdRandom.shuffle(randomqueue);
        }
        
        // Checks if index has traversed array length
        public boolean hasNext() { return i != N; }
        
        // This method is not supported
        public void remove() { throw new UnsupportedOperationException(); }
        
        // Return the ith element of this random instance of the queue
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return randomqueue[i++];
        }
    }
    
    /**
     * Unit tests random queue
     */
    public static void main(String[] args) {
        RandomizedQueue<String> r = new RandomizedQueue<String>();
        String item;
        
        StdOut.print("Inserted: ");
        while (!StdIn.isEmpty()) {
            item = StdIn.readString();
            StdOut.print(item + " ");
            r.enqueue(item);
        }
        StdOut.println();

        Iterator itB = r.iterator();
        Iterator itA = r.iterator();
        
        StdOut.print("1st Random Iteration: ");
        while (itA.hasNext()) {
            StdOut.print(itA.next() + " ");
        }
        StdOut.println();
        
        StdOut.print("2nd Random Iteration: ");
        while (itB.hasNext()) {
            StdOut.print(itB.next() + " ");
        }
        StdOut.println();
        
        StdOut.print("Randomly Dequeue: ");
        while (!r.isEmpty()) {
            StdOut.print(r.dequeue() + " ");
        }
        StdOut.println();
    }
}