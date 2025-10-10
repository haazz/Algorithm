import java.io.*;
import java.util.*;

public class Main {
    static final int N = 5;
    static int[] dy = { 0, 1, 0, -1 };
    static int[] dx = { 1, 0, -1, 0 };
    static int answer = 0;

    public static boolean can(char[][] board, boolean[][] visit, int y, int x) {

        int s = 0;
        int con = 0;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] bVisit = new boolean[N][N];
        q.add(new int[] { y, x - 1 });

        while (!q.isEmpty()) {
            int[] elem = q.poll();

            for (int d = 0; d < 4; d++) {
                int ny = dy[d] + elem[0];
                int nx = dx[d] + elem[1];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N || bVisit[ny][nx] || !visit[ny][nx]) {
                    continue;
                }

                if (board[ny][nx] == 'S') {
                    s++;
                }
                q.add(new int[] { ny, nx });
                bVisit[ny][nx] = true;
                con++;
            }
        }

        if (s < 4 || con < 7) {
            return false;
        }
        return true;
    }

    public static void comb(char[][] board, boolean[][] visit, int y, int x, int depth) {
        // 연결될 수 있는지 체크 + S가 4개 이상인지 체크
        if (depth >= 7) {
            if (can(board, visit, y, x)) {
                answer++;
            }
            return;
        }

        for (int i = y; i < N; i++) {
            int j = 0;
            if (i == y) {
                j = x;
            }
            for (; j < N; j++) {
                visit[i][j] = true;
                comb(board, visit, i, j + 1, depth + 1);
                visit[i][j] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        char[][] board = new char[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = s.charAt(j);
            }
        }
        comb(board, new boolean[N][N], 0, 0, 0);
        System.out.println(answer);
    }
}