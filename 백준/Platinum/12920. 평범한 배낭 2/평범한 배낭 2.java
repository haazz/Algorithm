import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<int[]> things = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int bin = 1;
            while (bin <= k) {
                things.add(new int[] { bin * v, bin * c });
                k -= bin;
                bin *= 2;
            }
            if (k != 0) {
                things.add(new int[] { k * v, k * c });
            }
        }

        int[][] dp = new int[things.size() + 1][M + 1];

        for (int i = 1; i < things.size() + 1; i++) {
            for (int j = 1; j <= M; j++) {
                int[] thing = things.get(i - 1);
                int prev = j - thing[0];

                if (prev < 0) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][prev] + thing[1]);
            }
        }
        // for (int i = 0; i < things.size() + 1; i++) {
        // for (int j = 0; j < M + 1; j++) {
        // System.out.print(dp[i][j] + " ");
        // }
        // System.out.println("");
        // }

        System.out.println(dp[things.size()][M]);
    }
}
