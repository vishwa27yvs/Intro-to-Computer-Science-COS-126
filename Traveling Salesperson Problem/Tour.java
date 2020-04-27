/************************************************************************************************
 * Compilation: javac-introcs Tour.java
 * Execution: java-introcs Tour
 * CircularQuote builds a circular linked list.
 *************************************************************************************************/

public class Tour {

    private Node first;   // Node that stores the starting point of the tour
    private Node x; // Reference node to traverse through the linked list
    //

    // creates a node class that stores a point and pointer to the next node
    private class Node {

        private Point point; // point present in the node
        private Node next;  //  next node

        /* private Node() {
            this.next = first;
        } */
    }

    // constructor to create empty Tour class
    public Tour() {

    }

    // test constructor that creates Tour class with 4 points as arguments
    public Tour(Point a, Point b, Point c, Point d) {

        Node x1 = new Node();
        Node x2 = new Node();
        Node x3 = new Node();
        Node x4 = new Node();
        x1.point = a;
        x2.point = b;
        x3.point = c;
        x4.point = d;
        first = x1;
        x1.next = x2;
        x2.next = x3;
        x3.next = x4;
        x4.next = x1;
    }

    // traverse each Node in the circularly linked list, starting from first,
    // and incrementins the counter at each step
    public int size() {
        x = first;
        int size = 0;
        do {
            size = size + 1;
            x = x.next;
        }
        while (x != first);

        return size;
    }

    // length() method accesses two successive points in the tour.
    // calls the distanceTo() method from the Point data type.
    public double length() {
        x = first;
        double length = 0;
        do {
            length = length + (x.point).distanceTo((x.next).point);
            x = x.next;
        }
        while (x != first);

        return length;
    }

    // appends each point to a StringBuilder object
    public String toString() {
        x = first;
        String path = "";
        do {
            path = path + ((x.point).toString()) + "\n";
            x = x.next;
        }
        while (x != first);
        return path;
    }

    // draws a line between any 2 point objects using StdDraw library
    public void draw() {
        x = first;
        double length = 0;
        do {
            (x.point).drawTo((x.next).point);
            x = x.next;
        }
        while (x != first);
    }

    /* public void addTo(String word) {
        if (first == null) {
            first = new Node(word);
            // System.out.println("start");
            x = first;
        }

        else {
            // traverse linked list until you find the last node

            // System.out.println(x.word);
            x.next = new Node(word);
            x = x.next;
            // System.out.println(x.word);
            // insert new word
        }
    } */

    // inserts the point nearest to a particular point p
    // computes the Euclidean distance
    public void insertNearest(Point p) {
        // if the Tour object is empty
        if (first == null) {
            first = new Node();
            first.point = p;
            first.next = first;
        }

        else {
            double min = p.distanceTo(first.point);
            Node temp1 = first;
            Node temp = new Node();
            temp.point = p;
            x = first.next;
            double length;
            // traverse through the entire circular linked list using do-while loop
            do {
                // stores the node containing the closest point and its distance to p
                length = p.distanceTo(x.point);
                if (length < min) {
                    temp1 = x;
                    min = length;
                }
                x = x.next;
            }
            while (x != first);
            // changing the next field of the newly created node.
            // changing the field of the closest node.
            Node temp2 = temp1.next;
            temp1.next = temp;
            temp.next = temp2;
        }
    }

    /* insert the point p which
       results  in the least possible increase in the total tour length.*/
    public void insertSmallest(Point p) {
        // if the Tour object is empty
        if (first == null) {
            first = new Node();
            first.point = p;
            first.next = first;
        }
        else {
            // actual keeps track of the initial length
            double actual = length();
            Node position = first;
            Node temp1 = first.next;
            Node temp = new Node();
            temp.point = p;
            first.next = temp;
            temp.next = temp1;
            double totLength = length() - actual;
            first.next = temp1;
            Node y = first.next;
            do {
                double dis1 = (y.point).distanceTo((y.next).point);
                temp1 = y.next;
                y.next = temp;
                temp.next = temp1;
                // inserting the point to find out the smallest distance
                double dis2 = (y.point).distanceTo(temp.point) +
                        (temp.point).distanceTo(temp1.point);
                if (dis2 - dis1 < totLength) {
                    position = y;
                    totLength = dis2 - dis1;
                }
                y.next = temp1;
                y = y.next;
            }

            while (y != first);
            Node temp2 = position.next;
            Node temp3 = new Node();
            temp3.point = p;
            position.next = temp3;
            temp3.next = temp2;

        }
    }

    // test client to check the Tour object
    public static void main(String[] args) {
        Point a = new Point(100.0, 100.0);
        Point b = new Point(500.0, 100.0);
        Point c = new Point(500.0, 500.0);
        Point d = new Point(100.0, 500.0);

        // create the tour a -> b -> c -> d -> a
        Tour squareTour = new Tour();
        squareTour.insertSmallest(a);
        StdOut.println(squareTour);
        System.out.println(squareTour.size());

        squareTour.insertSmallest(b);
        StdOut.println(squareTour);

        System.out.println(squareTour.size());
        squareTour.insertSmallest(c);
        StdOut.println(squareTour);
        System.out.println(squareTour.size());

        squareTour.insertSmallest(d);
        StdOut.println(squareTour);
        StdOut.println(squareTour.length());
        System.out.println(squareTour.size());


    }
}
