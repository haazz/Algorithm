
// 다이나믹 프로그래밍으로 풀기
// 연속 밟기에 조심해서 풀기 2차원 [3][N]으로 풀 수 있지 않을까?
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[][] board;
    static int answer = 0;
    static final int[] dy = { 0, 1, 0, -1 };
    static final int[] dx = { 1, 0, -1, 0 };

    public static void bfs(int[][] nBoard, int sy, int sx) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { sy, sx });

        while (!q.isEmpty()) {
            int[] pos = q.poll();

            for (int d = 0; d < 4; d++) {
                int ny = dy[d] + pos[0];
                int nx = dx[d] + pos[1];

                if (ny < 0 || ny >= N || nx < 0 || nx >= M || nBoard[ny][nx] != 0) {
                    continue;
                }
                nBoard[ny][nx] = 2;
                q.add(new int[] { ny, nx });
            }
        }
    }

    public static int calc(int[][] board) {
        int[][] nBoard = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                nBoard[i][j] = board[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (nBoard[i][j] != 2) {
                    continue;
                }
                bfs(nBoard, i, j);
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (nBoard[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void buildWall(int start, int wc) {
        if (wc <= 0) {
            answer = Math.max(answer, calc(board));
            return;
        }

        for (int i = start; i < N * M; i++) {
            int y = i / M;
            int x = i % M;

            if (board[y][x] == 1 || board[y][x] == 2) {
                continue;
            }
            board[y][x] = 1;
            buildWall(i + 1, wc - 1);
            board[y][x] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        buildWall(0, 3);
        System.out.println(answer);
    }
}