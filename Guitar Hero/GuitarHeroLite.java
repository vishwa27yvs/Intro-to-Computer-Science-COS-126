/*****************************************************************************
 * Compilation: javac-introcs GuitarHeroLite.java
 * Execution: java-introcs GuitarHeroLite
 * GuitarHeroLite.java is a sample GuitarString client
 * It plays guitar in real time, using the keyboard to input notes.
 **************************************************************************** */

public class GuitarHeroLite {

    public static void main(String[] args) {
        // Create two guitar strings, for concert A and C
        double CONCERT_A = 440.0;
        double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);
        GuitarString stringA = new GuitarString(CONCERT_A);
        GuitarString stringC = new GuitarString(CONCERT_C);

        while (true) {

            // Check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {

                char key = StdDraw.nextKeyTyped();
                // pluck the corresponding guitarstring
                if (key == 'a') {
                    stringA.pluck();

                }
                if (key == 'c') {
                    stringC.pluck();
                }
            }

            // compute the superposition of the samples
            double sample = stringA.sample() + stringC.sample();

            // send the result to standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            stringA.tic();
            stringC.tic();
        }
    }
}
