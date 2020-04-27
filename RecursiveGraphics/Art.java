/******************************************************************************
 * Compilation: javac-introcs Art.java
 * Dependencies: StdDraw.java, Transform2D.java
 * Execution: java-introcs Art
 * Art takes 1command line argument-the depth of the recursion for this pattern.
 * Art displays a snowflake pattern using recursion,
 * depending on the number of iterations/depth.
 *****************************************************************************/

public class Art {


    /* Computes the distance between 2 parallel sides of a hexagon
     * @ param  length of the hexagon
     * @ return width- the distance  */
    public static double width(double length) {
        double width = Math.sqrt(3.0) * length;
        return width;
    }


    /* Computes the distance between 2 opposite vertices of a hexagon
     * @ param  length of any side of regular hexagon
     * @ return width- the distance  */
    public static double height(double length) {
        double height = 2 * length;
        return height;
    }


    /* This draws the set of 7 hexagons forming a flake like pattern
     * @ param  x and y coordinates of the vertice facing north(reference vertex)
     * @ param length of the side of the hexagon*/
    public static void hexagonSet(double x, double y, double length) {
        // storing the 6 points in x and y position arrays
        double px[] = new double[6];
        double py[] = new double[6];
        // Writing the coordinates with reference to one vertex
        // Creating arrays for StdDraw.polygon function
        px[0] = x;
        px[1] = x - (width(length / 3.0)) / 2.0;
        px[2] = x - (width(length / 3.0)) / 2.0;
        px[3] = x;
        px[4] = x + (width(length / 3.0)) / 2.0;
        px[5] = x + (width(length / 3.0)) / 2.0;
        py[0] = y;
        py[1] = y - (height(length / 3.0)) / 4.0;
        py[2] = y - 3 * (height(length / 3.0)) / 4.0;
        py[3] = y - 4 * (height(length / 3.0)) / 4.0;
        py[4] = y - 3 * (height(length / 3.0)) / 4.0;
        py[5] = y - (height(length / 3.0)) / 4.0;
        // As we can see that the hexagons in the pattern are identical and
        // can be transformed by simply changing x and y co-ordinates
        // This co-ordinates can be obtained by geometrical formulas
        StdDraw.polygon(px, py);
        // Here we have called the translate functio from Transform2D.java
        Transform2D
                .translate(px, py, (2 * width(length) / 6.0),
                           -1 * (2 * height(length) / 12.0));
        StdDraw.polygon(px, py);
        Transform2D.translate(px, py, 0, -1 * height(length / 3.0));
        StdDraw.polygon(px, py);
        Transform2D
                .translate(px, py, -1 * (2 * width(length) / 6.0),
                           -1 * (2 * height(length) / 12.0));
        StdDraw.polygon(px, py);

        Transform2D
                .translate(px, py, -1 * (2 * width(length) / 6.0),
                           (2 * height(length) / 12.0));
        StdDraw.polygon(px, py);

        Transform2D.translate(px, py, 0, height(length / 3.0));
        StdDraw.polygon(px, py);
        Transform2D
                .translate(px, py, (2 * width(length) / 6.0),
                           -1 * (2 * height(length) / 12.0));
        StdDraw.polygon(px, py);

    }


    /* This our main recursion function
     * @ n passes the current depth of the pattern which has to be executed
     * @ param  x and y coordinates of the vertix facing north(reference vertex)
     * of the smaller hexagon.
     * @ param length of the side of the hexagon*/
    public static void flake(int n, double x, double y, double length) {
        if (n == 0) return;
        // drawing the set of 7 hexagons
        hexagonSet(x, y, length);
        // applying recursion on each of the 7 hexagons seperately
        flake(n - 1, x, y, length / 3.0);
        flake(n - 1, x + (2 * width(length) / 6.0), y - (2 * height(length) / 12.0),
              length / 3.0);
        flake(n - 1, x + (2 * width(length) / 6.0), y - (height(length) / 2.0),
              length / 3.0);
        flake(n - 1, x, y - (2 * height(length) / 3.0),
              length / 3.0);
        flake(n - 1, x - (2 * width(length) / 6.0), y - (height(length) / 2.0),
              length / 3.0);
        flake(n - 1, x - (2 * width(length) / 6.0), y - (2 * height(length) / 12.0),
              length / 3.0);
        flake(n - 1, x, y - height(length / 3.0), length / 3.0);


    }


    //  Takes an integer command-line argument n;
    // Draws a snowflake (hexagonal)pattern of depth n .
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        flake(n, 0.5, 1, 0.5);


    }
}
