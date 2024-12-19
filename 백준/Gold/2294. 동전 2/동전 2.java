import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static int[] coins;
    static int MAX_VALUE = 100000;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        coins = new int[N];
        int[] dp = new int[K + 1];
        for (int i = 0; i <= K; i++) {
            dp[i] = MAX_VALUE;
        }
        dp[0] = 0;
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
            for (int j = coins[i]; j <= K; j++) {
                dp[j] = Math.min(dp[j - coins[i]] + 1, dp[j]);
            }
        }
        if (dp[K] == MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(dp[K]);
        }
    }
}
