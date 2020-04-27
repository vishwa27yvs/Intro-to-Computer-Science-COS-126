/*******************************************************************************************
 * Compilation: javac-introcs Transform2D.java
 * Dependencies: StdDraw.java
 * Execution: java-introcs Transform2D
 * Transform2D can be used as a library and consists of mutations that can be performed
 * on a connected set of points.
 *************************************************************************************************/

public class Transform2D {
    /* This function scales the given polygon by a certain factor
     *@param x and y are 2 one dimensional arrays that store the x and y coordinates of each input point
     *@param alpha takes the factor by which you want to scale  the figure */
    public static void scale(double[] x, double[] y, double alpha) {
        int i;
        for (i = 0; i < x.length; i++) {
            // all dimensions scale by a factor alpha
            x[i] = alpha * x[i];
            y[i] = alpha * y[i];
        }
    }

    /* This function translates or shifts  the given polygon parallely along x axis and y axis
     *@param x and y are 2 one dimensional arrays that store the x and y coordinates of each input point
     *@param dx and dy takes the shift in x and y direction respectively */
    public static void translate(double[] x, double[] y, double dx, double dy) {
        int i;
        for (i = 0; i < x.length; i++) {
            // changing by parallel x and y distance
            x[i] = dx + x[i];
            y[i] = dy + y[i];
        }
    }

    /* This function rotates the given polygon by a particular angle
     * @param x and y are 2 one dimensional arrays that store the x and y coordinates of each input point
     * @param theta takes in the angle by which you want to rotate the figure */
    public static void rotate(double[] x, double[] y, double theta) {
        int i;
        double temp[] = new double[x.length];
        double temp1[] = new double[x.length];
        for (i = 0; i < x.length; i++) {
            // storing the values in temporary variables so the values are changed simultaneously
            temp[i] = (x[i] * Math.cos(Math.toRadians(theta))) - (y[i] * Math
                    .sin(Math.toRadians(theta)));
            temp1[i] = (y[i] * Math.cos(Math.toRadians(theta))) + (x[i] * Math
                    .sin(Math.toRadians(theta)));
            x[i] = temp[i];
            y[i] = temp1[i];
        }

    }

    /*@param x and y are 2 one dimensional arrays that store the x and y coordinates of each input point
     * The changes are made in the arrays by reference*/
    public static void copy(double[] x, double[] cx) {
        int i;
        for (i = 0; i < x.length; i++) {
            cx[i] = x[i];
        }
    }

    // Here main functions is used to test the functions created above
    public static void main(String[] args) {
        // setting the scale for the output
        StdDraw.setScale(-10.0, +10.0);
        double[] x = { 1, 1, 2, 2 };
        double[] y = { 1, 2, 1, 2 };
        double alpha = 2.0;
        double dx = 1.0;
        double dy = 2.0;
        double[] cx = new double[4];
        double[] cy = new double[4];
        copy(x, cx);
        copy(y, cy);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.polygon(cx, cy);
        // this will keep the original x and y arrays unchanged
        translate(cx, cy, dx, dy);
        StdDraw.setPenColor(StdDraw.ORANGE);
        StdDraw.polygon(x, y);

        scale(x, y, alpha);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.polygon(x, y);
        rotate(cx, cy, 45.0);

        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.polygon(cx, cy);

    }
}

