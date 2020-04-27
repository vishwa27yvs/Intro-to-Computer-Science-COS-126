/************************************************************************************************
 * Compilation: javac-introcs BeadTracker.java
 * Execution: java-introcs BeadTracker args[0] args[1] args[2]
 * Creates a data type BeadTracker, which takes 3 arguments- min pixels, tau and delta.
 * This class determines how far the bead moves from one frame to the next (in an interval of 0.5s)
 *************************************************************************************************/

public class BeadTracker {
    public static void main(String[] args) {
        int min = Integer.parseInt(args[0]);
        double tau = Double.parseDouble(args[1]);
        double delta = Double.parseDouble(args[2]);
        for (int a = 3; a < args.length - 1; a++) {
            Picture pic1 = new Picture(args[a]);
            Picture pic2 = new Picture(args[a + 1]);

            // creates 2 beadfinder objects for 2 consecutive frames
            BeadFinder frame1 = new BeadFinder(pic1, tau);
            BeadFinder frame2 = new BeadFinder(pic2, tau);

            // finds the beads in the frames depending on the min number of pixels
            Blob[] arr1 = frame1.getBeads(min);
            Blob[] arr2 = frame2.getBeads(min);

            for (int i = 0; i < arr2.length; i++) {

                double minLength = Double.POSITIVE_INFINITY;


                // for each ead in frame t+1 we find out the closest frame t
                for (int j = 0; j < arr1.length; j++) {
                    double d = arr2[i].distanceTo(arr1[j]);
                    if (d < minLength && d < delta) {
                        minLength = d;
                    }
                }
                // discarding distances greater than delta
                if (minLength <= delta) {
                    StdOut.printf("%.4f\n", minLength);

                }


            }


        }

    }
}

