import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int H;
    static int[][] board;
    static final int[] dy = { 0, -1, 0, 1 };
    static final int[] dx = { -1, 0, 1, 0 };

    public static void bfs(int cy, int cx) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { cy, cx, 0 });
        board[cy][cx] = 0;

        while (!q.isEmpty()) {
            int[] pos = q.poll();

            for (int d = 0; d < 4; d++) {
                int ny = dy[d] + pos[0];
                int nx = dx[d] + pos[1];

                if (ny < 0 || ny >= N || nx < 0 || nx >= M || board[ny][nx] != -1) {
                    continue;
                }
                q.add(new int[] { ny, nx, pos[2] + 1 });
                board[ny][nx] = pos[2] + 1;
            }
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        int sy = 0;
        int sx = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = -1 * Integer.parseInt(st.nextToken());
                if (board[i][j] == -2) {
                    sy = i;
                    sx = j;
                }
            }
        }

        bfs(sy, sx);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(board[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}