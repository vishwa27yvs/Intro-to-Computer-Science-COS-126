/*****************************************************************************
 * Compilation: javac-introcs RingBuffer.java
 * Execution: java-introcs RingBuffer
 * RingBuffer.java creates a datatype to model the ring buffer.
 * It builds the components for the Karplus-Strong algorithm
 **************************************************************************** */


public class RingBuffer {
    private double[] rb;         // items in the buffer
    private int first;           // index for the next dequeue or speek
    private int last;            // index for the next enqueue
    private int size;            // number of items in the buffer

    /* Creates an empty ring buffer with the specified capacity
     * @param capacity - of the ring buffer
     */
    public RingBuffer(int capacity) {
        rb = new double[capacity];
        // Initializing the index for enqueue
        first = 0;
        // Initializing the index for dequeue
        last = 0;
    }

    /* Method for testing the function */
    /* private void printBuffer() {
        for (int i = 0; i < rb.length; i++) {
            System.out.println(rb[i] + " ");
        }
    } */

    /* Method for testing the function */
    /* private void firstlast() {
        System.out.println(first);
        System.out.println(last);
        System.out.println(size);
    } */

    /* @return - the capacity of this ring buffer
     */
    public int capacity() {
        return rb.length;
    }

    /* Computes the number of items from the first and last positions
     * @return number of items currently in this ring buffer
     */
    public int size() {
        if (first > last) {
            size = rb.length + last - first;
        }
        else if (last > first) {
            size = last - first;
        }
        // first = last will have two cases, buffer can be full or empty
        // we consider this cases in the enqueue and dequeue operation
        return size;
    }

    /* Checks if the ring buffer is empty
     * @return True- if the ring buffer is empty, false otherwise
     */
    public boolean isEmpty() {
        boolean check = false;
        if (size == 0) {
            check = true;
        }
        return check;
    }

    /* Checks if the ring buffer is full
     * @return True- if the ring buffer is full, false otherwise */
    public boolean isFull() {
        boolean check = false;
        if (size == rb.length)
            check = true; // convert it to a direct boolean
        return check;
    }

    /* Adds item x to the end of this ring buffer
     * @param x- item that is added to the ened of the string buffer
     */
    public void enqueue(double x) {
        // If the ring buffer is already filled-throw a runtime exception
        if (isFull())
            throw new RuntimeException("You cannot add more elements");
        // Updating the index for dequeue
        if (last < (rb.length) - 1) {
            rb[last] = x;
            last = last + 1;
        }
        // We use the cyclic wrap around if we reach the end of ring buffer
        else {
            rb[rb.length - 1] = x;
            last = 0;
        }
        // Update the size after performing the dequeue operation
        if (last == first)
            size = rb.length;
        else
            size = size();


    }

    /* Deletes the item in the ring buffer at the index value stored in first
     * @return front- item at the front of the ring buffer */
    public double dequeue() {
        // If the ring buffer is  empty- throw a runtime exception
        if (isEmpty())
            throw new RuntimeException("You cannot delete more elements");

        double front;    // item at the front of the buffer

        // Updating the index for dequeue
        if (first < (rb.length) - 1) {
            front = rb[first];
            first = first + 1;
        }

        // We use the cyclic wrap around if we reach the end of ring buffer
        else {
            front = rb[rb.length - 1];
            first = 0;
        }

        // Update the size after performing the enqueue operation
        if (first == last)
            size = 0;
        else
            size = size();
        return front;
    }

    // @returns- the item at the front of this ring buffer
    public double peek() {
        if (isEmpty())
            throw new RuntimeException("You cannot delete more elements");
        return rb[first];
    }

    // tests and calls every instance method in this class
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]); // capacity
        RingBuffer buffer = new RingBuffer(n);
        for (int i = 1; i <= n; i++) {
            buffer.enqueue(i);
        }

        // buffer.firstlast();
        double t = buffer.dequeue();
        // buffer.firstlast();
        buffer.enqueue(t);
        // buffer.firstlast();

        StdOut.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) {
            double x = buffer.dequeue();
            double y = buffer.dequeue();
            buffer.enqueue(x + y);
            // buffer.firstlast();
        }
        StdOut.println(buffer.peek());

    }
}
