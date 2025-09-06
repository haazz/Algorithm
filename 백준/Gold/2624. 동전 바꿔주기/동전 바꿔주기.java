import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[][] coins = new int[K][2];
        int[][] dp = new int[K + 1][T + 1];
        dp[0][0] = 1;

        StringTokenizer st;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            coins[i][0] = Integer.parseInt(st.nextToken());
            coins[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < K; i++) {
            for (int j = 0; j < coins[i][1] + 1; j++) {
                for (int k = 0; k < T + 1; k++) {
                    int next = k + (coins[i][0] * j);
                    if (next > T) {
                        break;
                    }
                    // System.out.println(next + " " + k + " " + dp[i][k]);
                    dp[i + 1][next] += dp[i][k];
                }
            }
        }

        // for (int i = 0; i < K + 1; i++) {
        // for (int j = 0; j < T + 1; j++) {
        // System.out.print(dp[i][j]);
        // }
        // System.out.println();
        // }
        System.out.println(dp[K][T]);

    }
}