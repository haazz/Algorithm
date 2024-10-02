import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            int[][] things = new int[N][2];
            int[][] dp = new int[N + 1][K + 1];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                things[i][0] = Integer.parseInt(st.nextToken());
                things[i][1] = Integer.parseInt(st.nextToken());
            }

            /*
             * 1. 방문처리
             * 2.
             */
            for (int n = 1; n <= N; n++) {
                for (int k = 1; k <= K; k++) {
                    if (things[n - 1][0] > k) {
                        dp[n][k] = dp[n - 1][k];
                        continue;
                    }
                    dp[n][k] = Math.max(dp[n - 1][k], dp[n - 1][k - things[n - 1][0]] + things[n - 1][1]);
                }
            }

            System.out.println("#" + tc + " " + dp[N][K]);
        }
    }
}
