
/************************************************************************************************
 * Compilation: javac-introcs NBody.java
 * Dependencies: StdDraw.java, StdAudio.java, StdIn.java, StdOut.java
 * Execution: java-introcs NBody
 * NBody takes 2 command line arguments-the duration of simulation and simulation time increment dt.
 * It reads in the details of the universe to be simulated from standard input ising StdIn .
 * NBody simulates the motion of n bodies, mutually affected by gravitational forces of attraction.
 * It animates the result to standard drawing using StdDraw and prints the state of the universe
 at the end of the simulation
 *************************************************************************************************/

public class NBody {

    // force computed using Newton's law of universal gravitation
    // f = GRAVITATIONAL_CONSTANT * m1 * m2 / (distance)^2

    /* Computes the force acting on any body in x-direction.
     * @ param   x and y position of the 2 particles as arguments .
     * @ return forceX - the x component of gravitational force. */
    public static double forceX(double m1, double m2, double x1,
                                double y1, double x2, double y2) {
        double d = ((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2));
        final double GRAVITATIONAL_CONSTANT = 6.6700e-11;
        double forceX;
        if (d != 0) {
            // finding absolute value of cos of the angle for component
            double cosf = Math.abs(x1 - x2) / Math.sqrt(d);
            // considering the x-component by multiplying it with sin(angle)
            if (x2 > x1) {
                // checks +ve or -ve x-axis
                forceX = ((GRAVITATIONAL_CONSTANT * m2 * m1 * cosf) / d) * (1);
            }
            else {
                forceX = ((GRAVITATIONAL_CONSTANT * m2 * m1 * cosf) / d) * (-1);
            }

        }
        else {
            return 0;
        }
        return forceX;
    }

    /* Computes the force acting on any body in y-direction.
     * @ param   x and y position of the 2 particles as arguments .
     * @ return forceY - the y component of gravitational force. */
    public static double forceY(double m1, double m2, double x1,
                                double y1, double x2, double y2) {
        double d = ((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2));
        final double GRAVITATIONAL_CONSTANT = 6.6700e-11;
        double forceY;
        if (d != 0) {
            // finding absolute value of sin of the angle for component
            double sinf = Math.abs(y1 - y2) / Math.sqrt(d);
            // considering the y-component by multiplying it with sin(angle)
            if (y2 > y1) {
                // checks +ve or -ve y-axis
                forceY = ((GRAVITATIONAL_CONSTANT * m1 * m2 * sinf) / d) * (1);
            }
            else {
                forceY = ((GRAVITATIONAL_CONSTANT * m1 * m2 * sinf) / d) * (-1);
            }

        }
        else {
            return 0;
        }
        return forceY;
    }

    /**/
    public static void main(String[] args) {
        // duration of simulation
        double stoppingTime = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]); // simulation time increment
        int n = StdIn.readInt();                 // no. of particles
        double radius = StdIn.readDouble();      // radius of the universee
        // arrays to store data from standard input
        double px[] = new double[n];    // x coordinate
        double py[] = new double[n];    // y coordinate
        double vx[] = new double[n];    // speed in x direction
        double vy[] = new double[n];    // speed in y direction
        double mass[] = new double[n];  // mass of the particle
        String image[] = new String[n]; // image file stored in string format
        // reading from standard input(text file)
        for (int i = 0; i < n; i++) {
            px[i] = StdIn.readDouble();
            py[i] = StdIn.readDouble();
            vx[i] = StdIn.readDouble();
            vy[i] = StdIn.readDouble();
            mass[i] = StdIn.readDouble();
            image[i] = StdIn.readString();

        }

        // set the scale of the coordinate system depending on the radius
        StdDraw.setXscale((-1) * radius, radius);
        StdDraw.setYscale((-1) * radius, radius);
        StdDraw.enableDoubleBuffering();
        // playing audio using standard audio
        StdAudio.play("2001.wav");
        // variable to keep the record of the time passed
        double timePassed = 0.0;
        // main animation loop
        while (timePassed < stoppingTime) {
            // update the background
            StdDraw.picture(0.0, 0.0, "starfield.jpg");
            // creating and initializing arrays every time
            // finding out the forces in x and y direction at every iteration
            double[] fx = new double[n];
            double[] fy = new double[n];
            // Outer loop to update the net force in both directions
            for (int i = 0; i < n; i++) {
                // force applied by every other body on the 'i'th body
                for (int j = 0; j < n; j++) {
                    // calls NBody.force and passes the positions of both bodies
                    // updates the net force in both directions respectively
                    fx[i] = fx[i]
                            + NBody.forceX(mass[i], mass[j], px[i],
                                           py[i], px[j], py[j]);
                    fy[i] = fy[i]
                            + NBody.forceY(mass[i], mass[j], px[i],
                                           py[i], px[j], py[j]);
                }
            }
            for (int i = 0; i < n; i++) {
                // update velocity and postion after dt amount of time
                vx[i] = vx[i] + (dt * fx[i] / mass[i]);
                vy[i] = vy[i] + (dt * fy[i] / mass[i]);
                px[i] = px[i] + (dt * vx[i]);
                py[i] = py[i] + (dt * vy[i]);
            }
            // drawing the given particles using standard draw
            for (int i = 0; i < n; i++) {
                StdDraw.picture(px[i], py[i], image[i]);
            }
            // copy offscreen buffer to onscreen
            StdDraw.show();
            // pause for 20 ms
            StdDraw.pause(20);
            // incrementing the time passed
            timePassed = timePassed + dt;

        }
        // displays the final state of the universe
        StdOut.printf("%d\n", n);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < n; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          px[i], py[i], vx[i], vy[i], mass[i], image[i]);
        }

        StdAudio.close();
    }
}
