/*****************************************************************************
 * Compilation: javac-introcs GuitarString.java
 * Execution: java-introcs GuitarString
 * GuitarString.java creates a datatype to model a vibrating guitar string.
 **************************************************************************** */

public class GuitarString {

    static final double EDF = 0.996;  // Energy Decay Factor
    private RingBuffer guitarString0; // Declaring RingBuffer object

    /* Creates a guitar string of the specified capacity
     * StdAudio.SAMPLE_RATE = 44,100, number of samples each second
     * @param -frequency determines the capacity of the RingBuffer
     * Represents a guitar string at rest */
    public GuitarString(double frequency) {
        guitarString0 = new RingBuffer((int) Math.ceil(StdAudio.SAMPLE_RATE
                                                               / frequency));
        for (int i = 0; i < guitarString0.capacity(); i++) {
            guitarString0.enqueue(0);
        }
    }

    /* Initializes the contents of the ring buffer to the values in the array
     * @param -init array
     */
    public GuitarString(double[] init) {
        // here we have to use the enqueue function
        guitarString0 = new RingBuffer(init.length);
        for (int i = 0; i < init.length; i++) {
            // Initializing the contents to the values in the array
            guitarString0.enqueue(init[i]);
        }
    }

    // returns the number of samples in the ring buffer
    public int length() {
        return guitarString0.size();
    }


    // Plucks this guitar string (by replacing the ring buffer with white noise)
    public void pluck() {
        for (int i = 0; i < length(); i++) {
            // Replacing the present element in the buffer
            guitarString0.dequeue();
            // Replaces n items in the ring buffer with n random values
            guitarString0.enqueue(StdRandom.uniform(-0.5, 0.5));
        }
    }

    // Advances the Karplus-Strong simulation one time step
    public void tic() {
        // Applies Karplus-Strong update at the front of the ring buffer
        // enqueue average of the first 2 samples of the ring buffer
        // multiplied by the ENERGY DECAY FACTOR
        guitarString0.enqueue((EDF / 2) * (guitarString0.dequeue() +
                guitarString0.peek()));
    }

    // returns the current sample- value of the item at the front of the ring buffer
    public double sample() {
        return guitarString0.peek();
    }


    // tests and calls every constructor and instance method in this class
    public static void main(String[] args) {
        GuitarString obj1 = new GuitarString(441);
        System.out.println(obj1.length());
        System.out.println(obj1.sample());
        obj1.pluck();
        StdOut.printf("%8.4f\n", obj1.sample());
        double[] samples = { 0.2, 0.4, 0.5, 0.3, -0.2, 0.4, 0.3, 0.0, -0.1, -0.3 };
        GuitarString testString = new GuitarString(samples);
        int m = 25; // 25 tics
        for (int i = 0; i < m; i++) {
            double sample = testString.sample();
            StdOut.printf("%6d %8.4f\n", i, sample);
            testString.tic();
        }
    }
}
