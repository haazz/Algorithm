import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int N;
    static int result = Integer.MAX_VALUE;
    static int[][] board;
    static int[][] dp;
    static int MAX_VALUE = 16_000_000;

    public static int dfs(int visit, int node) {
        if (visit == ((1 << N) - 1)) {
            // System.out.println(visit + " " + node);
            // System.out.println(dp[visit][node]);
            // System.out.println(node + " " + dp[visit][node]);
            if (board[node][0] == 0) {
                return MAX_VALUE;
            }
            return board[node][0];
        }

        if (dp[visit][node] > 0) {
            return dp[visit][node];
        }
        dp[visit][node] = MAX_VALUE;

        for (int j = 1; j < N; j++) {
            if (board[node][j] == 0 || (visit & (1 << j)) != 0) {
                continue;
            }
            dp[visit][node] = Math.min(dfs(visit | (1 << j), j) + board[node][j], dp[visit][node]);
        }
        return dp[visit][node];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        dp = new int[1 << N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < (1 << N); i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(1, 0));
    }
}