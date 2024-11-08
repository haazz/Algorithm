import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int H;
    static int[][][] board;
    static Queue<int[]> evenTomato;
    static final int[] dy = { 0, -1, 0, 1, 0, 0 };
    static final int[] dx = { -1, 0, 1, 0, 0, 0 };
    static final int[] dz = { 0, 0, 0, 0, -1, 1 };

    public static int tomato() {
        int maxTime = 0;

        while (!evenTomato.isEmpty()) {
            int[] pos = evenTomato.poll();

            for (int d = 0; d < 6; d++) {
                int ny = dy[d] + pos[0];
                int nx = dx[d] + pos[1];
                int nz = dz[d] + pos[2];

                if (ny < 0 || ny >= N || nx < 0 || nx >= M || nz < 0 || nz >= H) {
                    continue;
                }
                if (board[nz][ny][nx] == 0) {
                    evenTomato.add(new int[] { ny, nx, nz, pos[3] + 1 });
                    board[nz][ny][nx] = 1;
                    maxTime = Math.max(maxTime, pos[3] + 1);
                }
            }
        }

        return check(maxTime);
    }

    public static int check(int maxTime) {
        boolean isAllZero = true;

        for (int h = 0; h < H; h++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[h][i][j] == 0) {
                        return -1;
                    }
                    if (board[h][i][j] == 1) {
                        isAllZero = false;
                    }
                }
            }
        }
        
        if (isAllZero) {
            return -1;
        }

        return maxTime;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        board = new int[H][N][M];
        evenTomato = new LinkedList<>();

        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    board[h][n][m] = Integer.parseInt(st.nextToken());
                    if (board[h][n][m] == 1) {
                        evenTomato.add(new int[] { n, m, h, 0 });
                    }
                }
            }
        }
        
        System.out.println(tomato());
    }
}