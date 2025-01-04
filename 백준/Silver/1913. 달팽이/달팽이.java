import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    static int N;
    static final int[] dy = { 1, 0, -1, 0 };
    static final int[] dx = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        int target = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];
        int resulty = 0;
        int resultx = 0;
        int y = 0;
        int x = 0;
        int d = 0;

        for (int i = N * N; i > 0; i--) {
            board[y][x] = i;
            if (i == target) {
                resulty = y + 1;
                resultx = x + 1;
            }
            int ny = y + dy[d];
            int nx = x + dx[d];

            if (ny < 0 || ny >= N || nx < 0 || nx >= N || board[ny][nx] != 0) {
                d = (d + 1) % 4;
                ny = y + dy[d];
                nx = x + dx[d];
            }

            y = ny;
            x = nx;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        sb.append(resulty).append(" ");
        sb.append(resultx);
        System.out.println(sb);
    }
}