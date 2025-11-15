
// 다이나믹 프로그래밍으로 풀기
// 연속 밟기에 조심해서 풀기 2차원 [3][N]으로 풀 수 있지 않을까?
import java.io.*;
import java.util.*;

public class Main {
    static final int[] dy = { 0, 1, 0, -1 };
    static final int[] dx = { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];
        int[][] dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = s.charAt(j) - '0';
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.add(new int[] { 0, 0, 0 });
        boolean[][] visit = new boolean[N][N];

        while (!pq.isEmpty()) {
            int[] elem = pq.poll();
            visit[elem[0]][elem[1]] = true;

            for (int d = 0; d < 4; d++) {
                int ny = dy[d] + elem[0];
                int nx = dx[d] + elem[1];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N || visit[ny][nx]) {
                    continue;
                }

                int cc = elem[2];
                if (board[ny][nx] == 0) {
                    cc++;
                }

                if (dp[ny][nx] > cc) {
                    dp[ny][nx] = cc;
                    pq.add(new int[] { ny, nx, cc });
                }
            }
        }

        System.out.println(dp[N - 1][N - 1]);
    }
}