import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int N;
    static int result = Integer.MAX_VALUE;

    public static boolean visitAll(boolean[] visit) {
        for (int i = 0; i < N; i++) {
            if (!visit[i]) {
                return false;
            }
        }
        return true;
    }

    public static void dfs(int[][] board, boolean[] visit, int node, int dist, int depth) {
        if (depth >= N - 1) {
            if (board[node][0] == 0) {
                return;
            }
            result = Math.min(result, dist + board[node][0]);
            return;
        }
        for (int j = 0; j < N; j++) {
            if (board[node][j] == 0 || visit[j]) {
                continue;
            }
            visit[j] = true;
            dfs(board, visit, j, dist + board[node][j], depth + 1);
            visit[j] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[] visit = new boolean[N];
        visit[0] = true;
        dfs(board, visit, 0, 0, 0);

        System.out.println(result);
    }
}