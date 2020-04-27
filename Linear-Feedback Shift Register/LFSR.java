/******************************************************************************
 * Compilation: javac-introcs LFSR.java
 * Execution: java-introcs LFSR
 * LFSR.java produce pseudo-random bits by simpulating a linear-feedback shift register
 **************************************************************************** */

public class LFSR {
    //  declaring an instance variables.
    private int[] ch;        // number of bits in the LFSR
    private int n;           // reg[i] = ith  bit of LFSR, reg[1] is rightmost bit
    private int tapPosition; // tap position

    /* creates an LFSR with the specified seed and tap
     * @param seed - initial LFSR string
     * @param tap - tapPosition */
    public LFSR(String seed, int tap) {
        n = seed.length();
        ch = new int[n];
        for (int i = 0; i < n; i++) {
            // ASCII values should be changed
            ch[i] = seed.charAt(i) - '0';
        }
        tapPosition = tap;
    }


    /* @return the number of bits in this LFSR */
    public int length() {
        return n;
    }


    /* computes the ith bit of the LFSR
     * @param i - the particular bit that you want to compute
     * returns the ith bit*/
    public int bitAt(int i) {
        return ch[n - i];
    }

    /* returns a string representation of this LFSR*/
    // Here you will require extra variables like sequence
    public String toString() {
        String seq = "";
        for (int i = 0; i < n; i++) {
            seq = seq + ch[i];
        }
        return seq;
    }

    /* simulates one step of this LFSR and returns the new bit (as 0 or 1)
     * computes the new LFSR string after one XOR operation, changes the ch[]bit array
     * @return bit after XOR operation */
    public int step() {
        int bit = bitAt(tapPosition) ^ bitAt(n);
        for (int i = 0; i < n - 1; i++) {
            ch[i] = ch[i + 1];
        }
        ch[n - 1] = bit;
        return bit;
    }

    /* simulates k steps of this LFSR and returns the k bits as a k-bit integer
     * computes the new LFSR string after k XOR operations
     * @return sum the decimal representation of k-bits  after k  XOR operations */
    public int generate(int k) {
        int sum = 0;

        for (int i = 0; i < k; i++) {
            sum = 2 * sum + step();
        }
        return sum;
    }

    // tests this class by directly calling all instance methods
    public static void main(String[] args) {
        LFSR lfsr0 = new LFSR("01101000010", 9);
        StdOut.println(lfsr0.toString());
        // simulates one step - 10 times
        for (int i = 0; i < 10; i++) {

            int bit = lfsr0.step();
            StdOut.println(lfsr0.toString() + " " + bit);
        }
        // simulates extraction of multiple bits
        for (int i = 0; i < 10; i++) {

            int val = lfsr0.generate(5);
            StdOut.println(lfsr0.toString() + " " + val);
        }
    }

}
