import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] coins;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            coins = new int[N];
            for (int i = 0; i < N; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
            M = Integer.parseInt(br.readLine());

            int[] dp = new int[M + 1];
            dp[0] = 1;

            for (int i = 0; i < N; i++) {
                for (int j = coins[i]; j <= M; j++) {
                    dp[j] += dp[j - coins[i]];
                }
            }
            sb.append(dp[M]).append("\n");
        }
        System.out.print(sb);
    }
}
