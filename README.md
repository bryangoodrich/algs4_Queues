Queues
------------------------

This assignment teaches how to implement iterators and generalize a stack/queue
hybrid called a double-sided queue (deque: pronounced "deck"). Included is also
a randomized queue/stack that once items are inserted, they are chosen
uniformly at random for extraction (pop) or sampling (peek). The Subset class
included is a simple client that makes use of the Random Queue by taking in
a first argument integer k that indicates how many randomly chosen items to
output. There is no error checking if k > N, the size of the string input.

The API is as follows

// A Deque: queue/stack like data structure with insert/extract from both ends
public class Deque<Item> implements Iterable<Item> {
    public Deque()                          // construct an empty deque
    public boolean isEmpty()                // is the deque empty?
    public int size()                       // return number of items in deque
    public void addFirst(Item item)         // add item to front of deque
    public void addLast(Item item)          // add item to end of deque
    public Item removeFirst()               // remove and return item from front
    public Item removeLast()                // remove and return item from end
    public Iterator<Item> iterator()        // return iterator starting at front
    public static void main(String[] args)  // unit testing
}

// A Random Queue: implement a random extraction queue/stack data structure
public class RandomQueue<Item> implements Iterable<Item> {
    public RandomQueue()                    // construct an empty queue
    public boolean isEmpty()                // is the queue empty?
    public int size()                       // return number of items in queue
    public void enqueue(Item item)          // add item
    public Item dequeue()                   // pop a random item
    public Item sample()                    // peek at random item
    public Iterator<Item> iterator()        // return iterator with indep. random ordering
    public static void main(String[] args)  // unit testing
}

// A Subsetting client: pull out k random elements from input strings
public class Subset {
    public static void main(String[] args)  // implement random queue
}

See JavaDocs for more details.

