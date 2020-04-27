/***************************************************************************
 * Compilation: javac-introcs GuitarHero.java
 * Execution: java-introcs GuitarHero
 * GuitarHeroLite.java is a sample GuitarString client
 * It plays guitar in real time, using the keyboard to input.
 * Supports a total of 37 notes.
 **************************************************************************** */

public class GuitarHero {
    public static void main(String[] args) {

        double CONCERT_A = 440.0;
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' \"";
        // Creates guitar strings for all 37 notes
        GuitarString[] string = new GuitarString[keyboard.length()];
        for (int i = 0; i < keyboard.length(); i++) {
            string[i] = new GuitarString(CONCERT_A *
                                                 Math.pow(2, (i - 24) / 12.0));
        }
        // the main input loop
        while (true) {
            // check if the user has typed a key, and, if so, process it

            if (StdDraw.hasNextKeyTyped()) {

                // the user types this character
                // converting to string to support 'contains' function
                String key = StdDraw.nextKeyTyped() + "";
                // If keystroke corresponds to one of the 37 possible notes
                if (keyboard.contains(key)) {
                    int i = keyboard.indexOf(key);
                    string[i].pluck();
                }
            }

            // compute the superposition of all different samples
            double sample = 0;
            for (int i = 0; i < keyboard.length(); i++) {
                sample = sample + string[i].sample();
            }

            // send the result to standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            for (int i = 0; i < keyboard.length(); i++) {
                string[i].tic();
            }
        }
    }
}
