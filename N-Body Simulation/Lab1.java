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

public class Lab1 {

    public static void main(String[] args) {
        System.out.println("Hello World");
        int a = 10;
        int b = 10;
        int sum = a + b;
        int s = 1;
        System.out.println(sum);
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                System.out.println("even" + sum);

            }
            else {
                System.out.println("odd");
            }
        }
    }
}
