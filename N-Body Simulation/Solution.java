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

public class Solution {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int a = Integer.parseInt(args[1]);
        System.out.println(n);
        int[] rate = new int[n];
        for (int i = 0; i < n; i++) {
            rate[i] = Integer.parseInt(args[2 + i]);
        }
        int actAmt = Integer.parseInt(args[2 + n]);
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (i == a) {
                continue;
            }
            else {
                sum = sum + rate[i];
            }
        }
        if ((sum / 2) == actAmt) {
            System.out.println("Bon Appetit");
        }
        else {
            System.out.println(actAmt - (sum / 2));
        }
    }
}
