package Recursion;

public class Recursion {
    public static long fac(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return n * fac(n - 1);
        }
    }
}
