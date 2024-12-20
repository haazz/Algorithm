import java.util.*;
import java.io.*;

public class Main {
    static int X;
    static int[] coins;
    static int[] coinValue = new int[] { 1, 5, 10, 25 };

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        X = Integer.parseInt(st.nextToken());
        coins = new int[4];
        int[][] dp = new int[X + 1][5];

        for (int i = 0; i < 4; i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= X; i++) {
            for (int j = 1; j < 5; j++) {
                if (i - coinValue[j - 1] < 0 ||
                        dp[i - coinValue[j - 1]][0] == -1 ||
                        dp[i - coinValue[j - 1]][j] + 1 > coins[j - 1]) {
                    continue;
                }
                if (dp[i - coinValue[j - 1]][0] + 1 > dp[i][0]) {
                    for (int k = 0; k < 5; k++) {
                        dp[i][k] = dp[i - coinValue[j - 1]][k];
                    }
                    dp[i][0]++;
                    dp[i][j]++;
                }
            }
            if (dp[i][0] == 0) {
                dp[i][0] = -1;
            }
        }
        for (int i = 1; i < 5; i++) {
            System.out.print(dp[X][i] + " ");
        }
    }
}
