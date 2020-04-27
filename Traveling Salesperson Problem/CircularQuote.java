/************************************************************************************************
 * Compilation: javac-introcs CircularQuote.java
 * Execution: java-introcs CircularQuote
 * CircularQuote builds a circular linked list.
 *************************************************************************************************/

public class CircularQuote {

    // the first card in the circular linked list
    private Node start;

    // node stores a word and a reference to the next node
    private class Node {
        private String word; // word in a sentence
        private Node next;   // next word

        private Node(String word) {
            this.word = word;
            this.next = null;
        }
    }

    private Node x;

    // creates an empty quote
    public CircularQuote() {

    }

    // adds a word to the end of the quote
    public void addWord(String word) {

        // degenerate case for empty quote
        if (start == null) {
            start = new Node(word);
            // System.out.println("start");
            x = start;
        }

        else {
            // traverse linked list until you find the last node

            // System.out.println(x.word);
            x.next = new Node(word);
            x = x.next;
            // System.out.println(x.word);
            // insert new word
        }
    }

    // number of words in the quote
    public int count() {
        if (start == null) {
            return 0;
        }
        else {
            // traverse linked list until you find the last node
            int count = 0;
            x = start;
            do {
                // System.out.println(x.word);
                count = count + 1;
                x = x.next;
            }
            while (x != null);

            // insert new word
            return count;
        }

    }

    // return the ith word where i = 1 is first word in quote
    public String getWord(int i) {
        // check for fewer than i words in quote or invalid index
        if (count() < i || i <= 0) {
            throw new RuntimeException("index out of bounds");
        }
        if (i == 1) {
            return start.word;
        }
        else {
            int num = 1;
            x = start;
            do {
                num = num + 1;
                x = x.next;
            }
            while (num < i);
            // insert new word
            return x.word;
        }

    }

    // insert specified word after the ith word (i = 1 is the first word)
    public void insertWord(int i, String word) {
        // check for fewer than i words in quote or invalid index
        if (count() < i || i <= 0)
            throw new RuntimeException("index out of bounds");

        // make Node for the new word
        Node temp;
        Node temp1;
        // find the ith card
        x = start;
        int num = 1;
        while (num < i) {
            num = num + 1;
            x = x.next;
        }
        temp1 = x.next;
        temp = new Node(word);
        x.next = temp;
        temp.next = temp1;

        // insert new word
    }

    // string representation of the entire quote
    public String toString() {
        if (start == null) {
            throw new NullPointerException("");
        }
        x = start;
        String quote = "";
        do {
            quote = quote + x.word + " ";
            x = x.next;
        }
        while (x != null);

        return quote;
    }

    // test client
    public static void main(String[] args) {
        CircularQuote quote = new CircularQuote();
        quote.addWord("A");
        quote.addWord("rose");
        quote.addWord("is");
        quote.addWord("a");
        quote.addWord("rose.");

        StdOut.println(quote.toString());
        StdOut.println(quote.count());
        StdOut.println(quote.getWord(2));
        quote.insertWord(3, "just");
        StdOut.println(quote);
        StdOut.println(quote.count());

    }
}
