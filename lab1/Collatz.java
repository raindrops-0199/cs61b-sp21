/** Class that prints the Collatz sequence starting from a given number.
 *  @author jason
 */
public class Collatz {

    /** buggy implementation of nextNumber! */
    public static int nextNumber(int n) {
        if (n == 1){
            return 1;
        }
        if (n % 2 == 0){
            return n / 2;
        }
        return 1;
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.print(n + " ");
        while (n != 1) {
            n = nextNumber(n);
            System.out.print(n + " ");
        }
        System.out.println();
    }
}

