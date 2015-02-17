/*****************************************************************************
 * Author:       Bryan Goodrich
 * Written:      2/14/2015
 * Last updated: 2/16/2015
 * 
 * Compilation:  javac Subset.java
 * Execution:    java Subset 3 < input.txt
 * Dependencies: RandomizedQueue.java StdOut.java StdIn.java
 * 
 * Returns k random strings from an input vector
 * 
 * % cat input.txt
 * A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
 * 
 * % java Subset 3 < input.txt
 * U
 * D
 * F
 ****************************************************************************/

/**
 * Return a Random Subset
 * 
 * This class is a simple client using a <i>Random Queue</i> for obtaining
 * a random subset of k < N (size of Queue) items.
 * The API here is designed by Robert Sedgewick and Kevin Wayne as part of
 * their online <i>Coursera</i> class.
 * 
 * For more resources, see the 
 * <a href="http://algs4.cs.princeton.edu/home/">Algorithms</a> homepage.
 * 
 * @author Bryan Goodrich
 */
public class Subset {
    
    /**
     * Unit test the client
     */
    public static void main(String[] args) {
        RandomizedQueue<String> r = new RandomizedQueue<String>();
        
        int k = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            r.enqueue(StdIn.readString());
        }
        
        for (int i = 0; i < k; i++) {
            StdOut.println(r.dequeue());
        }
    }
}
