import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static int K;
    static long[] fact;
    // static final long MAX_VALUE = 10_007;

    // public static long factorial(int x) {
    //     if (x == 1) {
    //         return fact[x] = 1;
    //     }
    //     return fact[x] = factorial(x - 1) * x;
    // }

    public static long func(int n, int k) {
        if (n == 0 || n < k) {
            return 0;
        } else if (k == 0 || n == k) {
            return 1;
        } else if (k == 1 || k == (n - 1)) {
            return n;
        }

        return func(n - 1, k - 1) + func(n - 1, k);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        fact = new long[N + 1];
        fact[0] = 1;

        // factorial(N);
        // for (int i = 0; i < N; i++) {
        //     System.out.println(fact[i]);
        // }
        // long result = ((fact[N] / fact[N - K]) /fact[K]) % MAX_VALUE;
        K = Math.min(N - K, K);
        System.out.println(func(N, K));
     }
}