/************************************************************************************************
 * Compilation: javac-introcs Blob.java
 * Execution: java-introcs Blob
 * Creates a data type Blob, which constructs an empty blob
 * Adds pixel to the blob, computes mass and calculates distance between 2 blobs.
 *************************************************************************************************/


public class Blob {

    private int numPixels; // number of pixels in the blobb
    private int sumX; // sum of all x co-ordinates
    private int sumY; // sum of all y co-ordinates

    // creates an empty blob
    public Blob() {
        numPixels = 0;
        sumX = 0;
        sumY = 0;
    }

    /* @param x- x co-ordinate of pixel
     * @param y- y co-ordinate of pixel
     * adds pixel (x, y) to this blob */
    public void add(int x, int y) {
        numPixels += 1;
        sumX += x;
        sumY += y;
    }


    /* returns the number of pixels added to this blob */
    public int mass() {
        return numPixels;
    }

    /* @param other Blob
     * returns the distance between the centre of mass of 2 blobs */
    public double distanceTo(Blob that) {

        double x1 = (double) (this.sumX) / (this.numPixels);
        double y1 = (double) (this.sumY) / (this.numPixels);
        double x2 = (double) (that.sumX) / (that.numPixels);
        double y2 = (double) (that.sumY) / (that.numPixels);
        double distance = Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
        return distance;
    }

    /* returns the string representation of the blob */
    public String toString() {
        String blob = "" + numPixels + " (" +
                String.format("%.4f", (double) (this.sumX) / (this.numPixels)) + "," +
                String.format("%.4f", (double) (this.sumY) / (this.numPixels)) + ") ";
        return blob;
    }


    /* tests this class by directly calling all instance methods */
    public static void main(String[] args) {
        Blob temp = new Blob();
        temp.add(0, 1);
        temp.add(1, 1);
        temp.add(1, 0);
        temp.add(0, 0);
        StdOut.println(temp);
        Blob temp2 = new Blob();
        temp2.add(0, 2);
        temp2.add(3, 2);
        temp2.add(2, 2);
        temp2.add(2, 1);
        StdOut.println(temp2);
    }
}
