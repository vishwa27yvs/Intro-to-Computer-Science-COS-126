
/*******************************************************************************************
 * Compilation: javac-introcs Sierpinski.java
 * Dependencies: StdDraw.java
 * Execution: java-introcs Sierpinski
 * Art takes 1 command line arguments-the depth of the recursion for this pattern.
 * Art displays the Sierpinski triangle , depending on the number of iterations/depth.
 *************************************************************************************************/

public class Sierpinski {


    /* @param - length of side of a triangle
     * @return-Height of an equilateral triangle whose sides are of the specified length.*/
    public static double height(double length) {
        double height;
        height = length * Math.sqrt(3) / 2;
        return height;
    }


    /*
     * @param- x and y coordinates of the bottom vertex
     * @param- lenght-side of the triangleDraws a filled equilateral triangle whose bottom vertex is (x, y)
     of the specified side length.*/
    public static void filledTriangle(double x, double y, double length) {
        double[] px = new double[3];
        double[] py = new double[3];
        // px and py store the coordinates in arrays
        px[0] = x - (length / 2.0);
        px[1] = x;
        px[2] = x + (length / 2.0);
        py[0] = y + height(length);
        py[1] = y;
        py[2] = y + height(length);
        // we created arrays to create arguments for filledPolygon function
        StdDraw.filledPolygon(px, py);
    }

    /* @param- n the depth of the step
     * @param- x and y coordinates of the bottom vertex
     * @param- lenght-side of the triangle
     * Draws a Sierpinski triangle of order n, such that the largest filled
     * triangle has bottom vertex (x, y) and sides of the specified length.*/
    public static void sierpinski(int n, double x, double y, double length) {
        if (n == 0) return;
        filledTriangle(x, y, length);
        // Calling the function for all new triangles formed.
        // We shall change the depth by 1 .
        // Expressing vertices in terms of bottom vertex
        sierpinski(n - 1, x - (length / 2.0), y, (length / 2.0));
        sierpinski(n - 1, x + (length / 2.0), y, (length / 2.0));
        sierpinski(n - 1, x, y + height(length), (length / 2.0));
    }


    //  Takes an integer command-line argument n;
    //  draws the outline of an equilateral triangle (pointed upwards) of length 1;
    //  whose bottom-left vertex is (0, 0) and bottom-right vertex is (1, 0); and
    //  draws a Sierpinski triangle of order n that fits snugly inside the outline.
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        StdDraw.line(0, 0, 1, 0);
        StdDraw.line(0, 0, 0.5, Math.sqrt(3.0) / 2.0);
        StdDraw.line(1, 0, 0.5, Math.sqrt(3.0) / 2.0);
        sierpinski(n, 0.5, 0, 0.5);
    }
}
