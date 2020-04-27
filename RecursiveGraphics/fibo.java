

/* *****************************************************************************
 *  Name:    Alan Turing
 *  NetID:   aturing
 *  Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */


public class fibo {

    static long[] memo = new long[100];
    static int i = 0;
    static int ang1 = 0;

    public static long F(int n) {
        if (n == 0) return 1;
        if (n == 1) return 2;
        if (memo[n] == 0)  // only if it is not calculated it will be calculated
        {
            memo[n] = F(n - 1) + F(n - 2);
        }
        return memo[n];
    }

    /**/
    public static void fibArc(int n, int i, double ang1) {
        if (i < n && i != 0) {
            if (i % 4 == 0) {

                StdDraw.arc(F(i - 1) - F(i), 0, F(n), ang1, ang1 + 90);
                i = i + 1;
            }
            else if (i % 4 == 1) {

                StdDraw.arc(0, F(i - 1) - F(i), F(n), ang1, ang1 + 90);
                i = i + 1;
            }
            else if (i % 4 == 2) {
                StdDraw.arc(F(i) - F(i - 1), 0, F(n), ang1, ang1 + 90);
                i = i + 1;
            }
            else {
                StdDraw.arc(0, F(i) - F(i - 1), F(n), ang1, ang1 + 90);
                i = i + 1;
            }
            fibArc(n, i, ang1 + 90);
        }
        else if (i == 0) {
            StdDraw.arc(0, 0, F(0), 0, 90);
            i = i + 1;
            fibArc(n, i, 90);

        }
        else return;
    }


    //  Takes an integer command-line argument n;
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        StdDraw.setXscale(-F(n), F(n));
        StdDraw.setYscale(-F(n), F(n));
        fibArc(n, 0, 0);
    }
}



