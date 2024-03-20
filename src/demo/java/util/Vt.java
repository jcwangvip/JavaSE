package demo.java.util;

import java.util.logging.Logger;

public class Vt {

    Logger logger = Logger.getLogger(getClass().getName());

    int fib(int N) {
        if (N <= 0) {
            return 0;
        }
        if (N == 1 || N == 2) return 1;
        return fib(N - 1) + fib(N - 2);
    }

    public static void main(String[] args) {
       /* Vt vt = new Vt();
        for (int i = 1; i <= 10; i++) {
            int fib = vt.fib(i);
            System.out.println(fib);
        }*/
        int i = 705599900;
        while (i >= 0) {
            try {
                int[] ix = new int[i];
                System.out.println(ix.length);
                break;
            } catch (OutOfMemoryError e) {
                i--;
            }
        }

    }


}
