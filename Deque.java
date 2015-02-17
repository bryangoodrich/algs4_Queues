/*****************************************************************************
 * Author:       Bryan Goodrich
 * Written:      2/14/2015
 * Last updated: 2/16/2015
 * 
 * Compilation:  javac Deque.java
 * Execution:    java Deque < input.txt
 * Dependencies: StdOut.java StdIn.java
 * 
 * Provides a two-sided queue (deque; pronounced "deck") with insertion and
 * extractions from either side. Implementation is a doubly linked-list with 
 * a single sentinal root node.
 * 
 * % cat input.txt
 * A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
 * 
 * % java Deque < input.txt
 * Deque contains: A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
 * Reversed: Z Y X W V U T S R Q P O N M L K J I H G F E D C B A
 * 
 ****************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Double-Sided Queue.
 * 
 * This class provides methods for implementing a deque data structure.
 * The API here is designed by Robert Sedgewick and Kevin Wayne as part of
 * their online <i>Coursera</i> class.
 * 
 * For more resources, see the 
 * <a href="http://algs4.cs.princeton.edu/home/">Algorithms</a> homepage.
 * 
 * @author Bryan Goodrich
 */
public class Deque<Item> implements Iterable<Item> {
    
    // Number of items in the deque
    private int N;
    
    // Sentinal node: maintains list start (root.next) and end (root.previous)
    private Node root;
    
    /** 
     * Creates a deque data structure
     * 
     * Initializes an empty sentinal node and 0 size list
     */
    public Deque() {
        root = new Node(null, null, null);
        N = 0;
    }
    
    /**
     * Checks if deque is empty
     * @return true if deque has no items; false otherwise
     */
    public boolean isEmpty() {
        return N == 0;
    }
    
    /**
     * Returns the number of items in deque
     * @return the number of items in deque
     */
    public int size() {
        return this.N;
    }
    
    /**
     * Initializes the first entry into an empty deque
     * 
     * @param item  a non-null list entry of Item type
     */
    private void init(Item item) {
        root.next = new Node(item, root, root);
        root.previous = root.next;
        N++;
    }
    
    /**
     * Add item to front of deque
     * 
     * @param item  a non-null list entry of Item type
     */
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();
        if (isEmpty()) { 
            init(item);
            return;
        }
        
        Node oldfirst = root.next;
        root.next = new Node(item, root, oldfirst);
        oldfirst.previous = root.next;
        N++;
    }
    
    /**
     * Add item to end of deque
     * 
     * @param item  a non-null list entry of Item type
     */
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();
        if (isEmpty()) {
            init(item);
            return;
        }
        
        Node oldlast = root.previous;
        root.previous = new Node(item, oldlast, root);
        oldlast.next = root.previous;
        N++;
    }
    
    /**
     * Remove and return item at front of deque
     * @return an item from front of deque
     */
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Node first = root.next;
        Item item = first.item;
        root.next = first.next;
        first.next.previous = root;
        N--;
        return item;
    }
    
    /**
     * Remove and return item at end of deque
     * @return an item from end of deque
     */
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Node last = root.previous;
        Item item = last.item;
        root.previous = last.previous;
        last.previous.next = root;
        N--;
        return item;
    }
    
    /**
     * Return an iterator over items starting at front of deque
     * @return an iterator over items in deque
     */
    public Iterator<Item> iterator() { return new DequeIterator(); }
        
    // A doubly linked-list node with trivial constructor
    private class Node {
        
        // Node data
        private Item item;
        
        // Node pointer to previous list entry
        private Node previous;
        
        // Node pointer to next list entry
        private Node next;
        
        // Initialize node with pointers to two other nodes
        public Node(Item item, Node previous, Node next) {
            this.item = item;
            this.previous = previous;
            this.next = next;
        }
    }
    
    /**
     * An iterator over a deque
     * 
     * This iterator starts at front of deque and does not instantiate
     * the remove() method.
     */
    private class DequeIterator implements Iterator<Item> {
        
        // Node pointing to start of deque
        private Node current = root.next;
        
        // Checks if deque has been traversed
        public boolean hasNext() { return current != root; }
        
        // This method is not supported
        public void remove() { throw new UnsupportedOperationException(); }
        
        // Returns current item and iterates deque
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    
    /**
     * Unit testing string entries from standard input
     */
    public static void main(String[] args) {
        Deque<String> d = new Deque<String>();
        
        while (!StdIn.isEmpty()) {
            d.addLast(StdIn.readString());
        }
        
        Iterator it = d.iterator();
        
        StdOut.print("Deque contains: ");
        while (it.hasNext()) {
            StdOut.print(it.next() + " ");
        }
        StdOut.println();
        
        StdOut.print("Reversed: ");
        while (!d.isEmpty()) {
            StdOut.print(d.removeLast() + " ");
        }
        StdOut.println();
    }
}