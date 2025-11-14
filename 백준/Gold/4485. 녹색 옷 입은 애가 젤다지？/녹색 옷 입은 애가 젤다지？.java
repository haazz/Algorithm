import java.io.*;
import java.util.*;

public class Main {
    static int N = 0;
    static int[][] board;
    static int[][] dp = new int[125][125];
    static final int[] dy = { 0, 1, 0, -1 };
    static final int[] dx = { 1, 0, -1, 0 };

    public static int sol() {
        boolean[][] visit = new boolean[N][N];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[2] - b[2];
        });

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[0][0] = board[0][0];
        pq.add(new int[] { 0, 0, board[0][0] });

        while (!pq.isEmpty()) {
            int[] e = pq.poll();
            visit[e[0]][e[1]] = true;

            for (int d = 0; d < 4; d++) {
                int ny = e[0] + dy[d];
                int nx = e[1] + dx[d];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N || visit[ny][nx] || dp[ny][nx] < e[2] + board[ny][nx]) {
                    continue;
                }
                dp[ny][nx] = e[2] + board[ny][nx];
                pq.add(new int[] { ny, nx, dp[ny][nx] });

            }
        }

        return dp[N - 1][N - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int pn = 1;
        StringBuilder sb = new StringBuilder();

        while (true) {
            N = Integer.parseInt(br.readLine());

            if (N == 0) {
                break;
            }

            board = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append("Problem " + pn + ": " + sol() + "\n");
            pn++;
        }

        System.out.println(sb.toString());
    }
}