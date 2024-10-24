import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N;
    static char[][] board;
    static boolean[][] visit;
    static final int[] dy = { 0, 1, 0, -1 };
    static final int[] dx = { 1, 0, -1, 0 };

    public static void bfs(int cy, int cx, char ch) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { cy, cx });
        visit[cy][cx] = true;
        if (ch == 'G') {
            board[cy][cx] = 'R';
        }

        while (!q.isEmpty()) {
            int[] elem = q.poll();

            for (int d = 0; d < 4; d++) {
                int ny = elem[0] + dy[d];
                int nx = elem[1] + dx[d];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N
                        || visit[ny][nx] || board[ny][nx] != ch) {
                    continue;
                }

                // 적록색약용으로 board 업데이트
                if (ch == 'G') {
                    board[ny][nx] = 'R';
                }

                visit[ny][nx] = true;
                q.add(new int[] { ny, nx });
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new char[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();

            for (int j = 0; j < N; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        int originalCnt = 0;
        visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visit[i][j]) {
                    continue;
                }
                bfs(i, j, board[i][j]);
                originalCnt++;
            }
        }

        int grCnt = 0;
        visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visit[i][j]) {
                    continue;
                }
                bfs(i, j, board[i][j]);
                grCnt++;
            }
        }

        System.out.println(originalCnt + " " + grCnt);
    }
}