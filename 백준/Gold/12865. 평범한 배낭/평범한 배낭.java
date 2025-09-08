import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] things = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            things[i][0] = Integer.parseInt(st.nextToken());
            things[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][K + 1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= K; j++) {
                int prev = j - things[i][0];
                if (prev < 0) {
                    dp[i + 1][j] = dp[i][j];
                    continue;
                }
                dp[i + 1][j] = Math.max(dp[i][j], dp[i][prev] + things[i][1]);
            }
        }
        // for (int i = 0; i < N + 1; i++) {
        // for (int j = 0; j < K + 1; j++) {
        // System.out.print(dp[i][j] + " ");
        // }
        // System.out.println("");
        // }
        System.out.println(dp[N][K]);
    }
}