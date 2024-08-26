import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    static int N;
    static final int[] dy = { 0, 1, 0, -1, -1, 1, 1, -1 };
    static final int[] dx = { 1, 0, -1, 0, -1, 1, -1, 1 };

    public static void bfs(char[][] board, int ci, int cj) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { ci, cj });

        while (!q.isEmpty()) {
            int[] elem = q.poll();

            // 주변에 지뢰가 있다면 탐색을 멈춥니다.
            boolean existTrap = false;
            for (int d = 0; d < 8; d++) {
                int ny = dy[d] + elem[0];
                int nx = dx[d] + elem[1];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N) {
                    continue;
                }
                if (board[ny][nx] == '*') {
                    existTrap = true;
                    break;
                }
            }

            if (existTrap) {
                continue;
            }

            // 8방 탐색을 통해 bfs 동작
            for (int d = 0; d < 8; d++) {
                int ny = dy[d] + elem[0];
                int nx = dx[d] + elem[1];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N
                        || board[ny][nx] == '*' || board[ny][nx] == 2) {
                    continue;
                }
                board[ny][nx] = 2;
                q.add(new int[] { ny, nx });
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            int cnt = 0;
            char[][] board = new char[N][N];

            for (int i = 0; i < N; i++) {
                String s = br.readLine();
                for (int j = 0; j < N; j++) {
                    // 지뢰가 있는 주변 8방향을 모두 1로 업데이트 해줍니다.
                    if (s.charAt(j) == '*') {
                        for (int d = 0; d < 8; d++) {
                            int ny = dy[d] + i;
                            int nx = dx[d] + j;

                            if (ny < 0 || ny >= N || nx < 0 || nx >= N
                                    || board[ny][nx] == '*') {
                                continue;
                            }
                            board[ny][nx] = 1;
                        }
                        board[i][j] = s.charAt(j);
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] == 0) {
                        bfs(board, i, j);
                        cnt++;
                    }
                }
            }

            // 단일로 남아있는 칸에 대해서 세어줍니다.
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] == 1) {
                        cnt++;
                    }
                }
            }

            System.out.println("#" + tc + " " + cnt);
        }
    }
}