/************************************************************************************************
 * Compilation: javac-introcs MarkovModel.java
 * Execution: java-introcs MarkovModel
 * Creates an immutable data type MarkovModel of order k from a given text string
 *************************************************************************************************/

public class MarkovModel {

    // input sequence of characters over the ASCII alphabet
    // all char values between 0 and 127
    public static int key;
    private static final int ASCII = 128;
    // ST to associate with the frequency of a particular string
    private ST<String, Integer> st1;
    // ST to associate with the frequency of each of the ASCII character following a string
    private ST<String, int[]> st2;
    // order of the MarkovModel
    private int order;


    /* creates a Markov model of order k for the specified text
     * @param text - string for MarkovModel
     * @param k - order for the MarkovModel
     */
    public MarkovModel(String text, int k) {

        key += 1;
        st1 = new ST<String, Integer>();
        st2 = new ST<String, int[]>();
        String s = text + text.substring(0, k);
        // create a circular string
        order = k;
        int[] freq; // stores the freq

        for (int i = 0; i < text.length(); i++) {
            // initializing the key for symbol tables
            String temp0 = s.substring(i, i + k);
            char temp1 = s.charAt(i + k);
            // incrementing the frequency of the particular key
            if (!st1.contains(temp0)) st1.put(temp0, 1);
            else st1.put(temp0, st1.get(temp0) + 1);
            // incrementing the frequency of the particular key
            if (!st2.contains(temp0)) {
                freq = new int[ASCII];
                // java automatically promotes char to an int if used as an index in an array
                freq[temp1] = 1;
                st2.put(temp0, freq);
            }
            else {
                freq = st2.get(temp0);
                freq[temp1] = freq[temp1] + 1;
                st2.put(temp0, freq);
            }
        }
    }

    /* returns the order k of this Markov model */
    public int order() {
        return order;
    }


    /* returns a string representation of the Markov model (as described below)*/
    public String toString() {
        String result = "";
        for (String key : st2.keys()) {
            result += key + ": ";
            int[] freq = (st2.get(key));
            for (char i = 0; i < ASCII; i++) {
                if (freq[i] != 0)
                    result += i + " " + freq[i] + " ";
            }
            result += "\n";
        }

        return result;
    }


    /* @param kgram - string whose frequency has to be calculated
     * returns the number of times the specified kgram appears in the text */
    public int freq(String kgram) {
        if (kgram.length() == order) {
            if (!st1.contains(kgram))
                return 0;
            else
                return st1.get(kgram);
        }
        else
            throw new IllegalArgumentException();
    }


    /* @param kgram - string kgram - key
     * @param c- character whose frequency has to be calculated
     * returns the number of times the character c follows the specified kgram in the text */
    public int freq(String kgram, char c) {
        if (kgram.length() == order) {
            if (!st2.contains(kgram))
                return 0;
            else
                return st2.get(kgram)[c];
        }
        else
            throw new IllegalArgumentException();
    }


    /* returns a random character that follows the specified kgram in the text,
     * chosen with weight proportional to the number of times that character
     * follows the specified kgram in the text */
    public char random(String kgram) {
        if (st2.contains(kgram))
            return (char) StdRandom.discrete(st2.get(kgram));
        else
            throw new IllegalArgumentException("Specified key could not be found");
    }

    // tests this class by directly calling all instance methods
    public static void main(String[] args) {
        String text1 = "banana";
        MarkovModel model1 = new MarkovModel(text1, 2);
        StdOut.println(model1);

        // StdOut.println(model1.order);
        // StdOut.println(model1.key);
        StdOut.println("freq(\"an\", 'a')    = " + model1.freq("an", 'a'));
        StdOut.println("freq(\"na\", 'b')    = " + model1.freq("na", 'b'));
        StdOut.println("freq(\"na\", 'a')    = " + model1.freq("na", 'a'));
        StdOut.println("freq(\"na\")         = " + model1.freq("na"));
        StdOut.println();

        String text3 = "one fish two fish red fish blue fish";
        MarkovModel model3 = new MarkovModel(text3, 4);
        // StdOut.println(model3.key);
        StdOut.println("freq(\"ish \", 'r') = " + model3.freq("ish ", 'r'));
        StdOut.println("freq(\"ish \", 'x') = " + model3.freq("ish ", 'x'));
        StdOut.println("freq(\"ish \")      = " + model3.freq("ish "));
        StdOut.println("freq(\"tuna\")      = " + model3.freq("tuna"));

        char a = model1.random("na");
        StdOut.println(a);


    }
}

