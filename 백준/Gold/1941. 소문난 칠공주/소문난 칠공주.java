// 4명의 S를 반드시 갖고 있어야 한다.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1. 7개가 될 수 있는 조합 구하기
 * 2. 연결된 조합인지 확인
 * 3. + 이다솜파 (S) 4개가 포함 되는지 체크
 */
class Main {
    static final int N = 5;
    static final int[] dy = { 0, -1, 0, 1 };
    static final int[] dx = { -1, 0, 1, 0 };
    static int result;

    public static boolean isMoreFourY(char[][] board, boolean[][] visit) {
        // 'Y'가 4개 이상인지 체크
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visit[i][j] && board[i][j] == 'S') {
                    cnt++;
                }
            }
        }

        if (cnt >= 4) {
            return true;
        }
        return false;
    }

    public static boolean bfs(boolean[][] visit, int ci, int cj) {
        int cnt = 0;
        boolean[][] bfsVisit = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { ci, cj });

        while (!q.isEmpty()) {
            int[] elem = q.poll();
            bfsVisit[elem[0]][elem[1]] = true;
            cnt++;

            for (int d = 0; d < 4; d++) {
                int ny = dy[d] + elem[0];
                int nx = dx[d] + elem[1];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N
                        || bfsVisit[ny][nx] || !visit[ny][nx]) {
                    continue;
                }
                q.add(new int[] { ny, nx });
                bfsVisit[ny][nx] = true;
            }
        }

        if (cnt == 7) {
            return true;
        }
        return false;
    }

    public static boolean isConnect(char[][] board, boolean[][] visit) {
        // bfs를 이용한 연결 체크
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit[i][j]) {
                    continue;
                }
                return bfs(visit, i, j);
            }
        }
        return false;
    }

    public static void dfs(char[][] board, boolean[][] visit, int start, int depth) {
        if (depth >= 7) {
            if (isMoreFourY(board, visit) && isConnect(board, visit)) {
                result++;
            }
            return;
        }

        // 1. 조합 구하기
        for (int i = start / 5; i < N; i++) {
            int j = 0;
            if (i == start / 5) {
                j = start % 5;
            }
            for (; j < N; j++) {
                if (visit[i][j]) {
                    continue;
                }
                visit[i][j] = true;
                dfs(board, visit, (i * 5) + j + 1, depth + 1);
                visit[i][j] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        char[][] board = new char[N][N];
        List<int[]> lees = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                board[i][j] = s[j].charAt(0);
                if (board[i][j] == 'S') {
                    lees.add(new int[] { i, j });
                }
            }
        }

        dfs(board, new boolean[N][N], 0, 0);
        System.out.println(result);

    }
}