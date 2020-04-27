/************************************************************************************************
 * Compilation: javac-introcs Avogadro.java
 * Execution: java-introcs Avogadro
 * Avogadro estimates the Boltzmann constant and Avogadro's number.
 *************************************************************************************************/

public class Avogadro {
    public static final double METERPERPIXEL = 0.175e-6;
    public static final double TEMP = 297;
    public static final double VISCOSITY = 9.135e-4;
    public static final double RAD = 0.5e-6;
    public static final double R = 8.31446;

    public static void main(String[] args) {
        int numP = 0;
        double varSum = 0.0;
        double k;
        double D;
        double NA;
        while (!StdIn.isEmpty()) {
            // takes radial displacents as input form StdIn
            double value = (METERPERPIXEL) * StdIn.readDouble();
            numP += 1;
            varSum += value * value;
        }
        D = varSum / (2 * numP);
        k = (6 * D * (Math.PI) * (VISCOSITY) * (RAD)) / TEMP;
        NA = R / k;

        StdOut.printf("Boltzmann = %.4e\n", k);
        StdOut.printf("Avogadro  = %.4e", NA);
        System.out.println();
    }
}
