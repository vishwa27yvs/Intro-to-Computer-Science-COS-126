/************************************************************************************************
 * Compilation: javac-introcs TextGeneration.java
 * Execution: java-introcs TextGeneration
 * Takes two integer command-line arguments k(order) of the Markov Model
 * It generates T characters by simulating a trajectory through the corresponding Markov chain
 ***********************************************************************************************/


public class TextGeneration {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        char a;
        String input = StdIn.readAll();
        // System.out.print(input);
        MarkovModel text = new MarkovModel(input, k);
        System.out.println(MarkovModel.key);
        // using the first k characters as the initial k-gram
        String temp = input.substring(0, k);
        StdOut.print(input.substring(0, k));
        for (int i = 0; i < t - k; i++) {
            // generates,stores and prints a random character for the specific k-gram
            a = text.random(temp.substring(i, i + k));
            StdOut.print(a);
            // update the k-gram to store the last k characters printed
            temp = "" + temp + a;
        }
        System.out.println();
    }
}
