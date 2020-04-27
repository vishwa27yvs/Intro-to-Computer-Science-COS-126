/* *****************************************************************************
 *  Name:    Alan Turing
 *  NetID:   aturing
 *  Precept: P00
 *
 *  Partner Name:    Ada Lovelace
 *  Partner NetID:   alovelace
 *  Partner Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */
// PlayThatTuneDeluxe
public class PlayThatTune {
    public static double[] superpose(double[] a, double[] b, double awt, double bwt) {
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i] * awt + b[i] * bwt;
        }
        return c;
    }

    // notice here we have changed the argument
    public static double[] tone(double hz, double t) {

        // read in the pitch, where 0 = Concert A (A4)
        // read in duration in seconds
        // build sine wave with desired frequency
        // double hz = 440 * Math.pow(2, hz / 12.0)
        int n = (int) (StdAudio.SAMPLE_RATE * t);
        double[] a = new double[n + 1];
        for (int i = 0; i <= n; i++) {
            a[i] = Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE);
        }
        // play it using standard audio
        // StdAudio.play(a)
        return a;
    }

    public static double[] note(int pitch, double t) {
        double hz = 440 * Math.pow(2, pitch / 12.0);
        double[] a = tone(hz, t);
        double[] hi = tone(2 * hz, t);
        double[] lo = tone(hz / 2, t);
        double[] h = superpose(hi, lo, 0.5, 0.5);
        return superpose(a, h, 0.5, 0.5);

    }

    public static void main(String[] args) {
        while (!StdIn.isEmpty()) {
            int pitch = StdIn.readInt();
            double duration = StdIn.readDouble();
            // note is used as the main function
            double[] a = note(pitch, duration);
            StdAudio.play(a);
        }

    }
}
