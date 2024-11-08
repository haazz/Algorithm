import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[][] board;
    static Queue<int[]> evenTomato;
    static final int[] dy = { 0, -1, 0, 1 };
    static final int[] dx = { -1, 0, 1, 0 };

    public static int tomato() {
        int maxTime = 0;

        while (!evenTomato.isEmpty()) {
            int[] pos = evenTomato.poll();

            for (int d = 0; d < 4; d++) {
                int ny = dy[d] + pos[0];
                int nx = dx[d] + pos[1];

                if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
                    continue;
                }
                if (board[ny][nx] == 0) {
                    evenTomato.add(new int[] { ny, nx, pos[2] + 1 });
                    board[ny][nx] = 1;
                    maxTime = Math.max(maxTime, pos[2] + 1);
                }
            }
        }

        return check(maxTime);
    }

    public static int check(int maxTime) {
        boolean isAllZero = true;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) {
                    return -1;
                }
                if (board[i][j] == 1) {
                    isAllZero = false;
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
        board = new int[N][M];
        int[] startIdx = new int[2];
        evenTomato = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    evenTomato.add(new int[] { i, j, 0 });
                }
            }
        }
         
        System.out.println(tomato());
    }
}