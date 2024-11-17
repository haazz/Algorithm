import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static char[][] board;
    static int[] dy = { 0, -1, 0, 1 };
    static int[] dx = { -1, 0, 1, 0 };
    static int result = 11;

    public static int[] move(int y, int x, int d) {
        int ny = y;
        int nx = x;
        boolean isAnotherOne = false;
        char startChar = board[y][x];

        while (board[ny][nx] != 'O' && board[ny][nx] != '#') {
            if ((board[ny][nx] != startChar) && (board[ny][nx] == 'B' || board[ny][nx] == 'R')) {
                isAnotherOne = true;
            }
            ny += dy[d];
            nx += dx[d];
        }

        if (board[ny][nx] == '#') {
            ny -= dy[d];
            nx -= dx[d];
        }
        if (board[ny][nx] != 'O' && isAnotherOne) {
            ny -= dy[d];
            nx -= dx[d];
        }
        return new int[] { ny, nx };
    }

    public static void dfs(int ry, int rx, int by, int bx, int cnt) {
        if (cnt >= result) {
            return;
        }

        for (int d = 0; d < 4; d++) {
            int[] nr = move(ry, rx, d);
            int[] nb = move(by, bx, d);

            if (board[nb[0]][nb[1]] == 'O') {
                continue;
            }
            if (board[nr[0]][nr[1]] == 'O') {
                result = Math.min(result, cnt);
                return;
            }

            board[ry][rx] = '.';
            board[by][bx] = '.';
            board[nr[0]][nr[1]] = 'R';
            board[nb[0]][nb[1]] = 'B';

            // if (cnt < 3) {
            // System.out.println(cnt + " " + d);
            // for (int i = 0; i < N; i++) {
            // for (int j = 0; j < M; j++) {
            // System.out.print(board[i][j]);
            // }
            // System.out.println();
            // }
            // }

            dfs(nr[0], nr[1], nb[0], nb[1], cnt + 1);

            board[nr[0]][nr[1]] = ',';
            board[nb[0]][nb[1]] = '.';
            board[ry][rx] = 'R';
            board[by][bx] = 'B';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        int[] blue = new int[2];
        int[] red = new int[2];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'R') {
                    red[0] = i;
                    red[1] = j;
                }
                if (board[i][j] == 'B') {
                    blue[0] = i;
                    blue[1] = j;
                }
            }
        }

        dfs(red[0], red[1], blue[0], blue[1], 1);
        if (result == 11) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }

    }
}