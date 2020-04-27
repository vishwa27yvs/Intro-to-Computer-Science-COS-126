/************************************************************************************************
 * Compilation: javac-introcs LinkedBlob.java
 * Execution: java-introcs LinkedBlob
 * Creates a data type LinkedBlob, consists a linked list of blobs, where each node points to the next node
 *************************************************************************************************/


public class LinkedBlob {

    private Node first; // points to the 1st blob

    private class Node {
        Blob item; // item present inn the node
        Node next; // pointer to the next node
    }

    /* checks if the linked list is empty */
    public Boolean isEmpty() {
        return (first == null);
    }


    /* adds an element at the end of the list */
    public void push(Blob item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }


    /* removes and returns element from the start of the list */
    public Blob pop() {
        Blob item = first.item;
        first = first.next;
        return item;
    }


}
