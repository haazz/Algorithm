import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R;
    static int C;
    static int maxCount;
    static char[][] board;
    static boolean[] isVist;
    static final int[] dy = { 1, 0, -1, 0 };
    static final int[] dx = { 0, -1, 0, 1 };

    public static void dfs(int cy, int cx, int count) {
        maxCount = Math.max(maxCount, count);
        isVist[board[cy][cx] - 'A'] = true;

        for (int d = 0; d < 4; d++) {
            int ny = dy[d] + cy;
            int nx = dx[d] + cx;

            if (ny < 0 || ny >= R || nx < 0 || nx >= C || isVist[board[ny][nx] - 'A']) {
                continue;
            }
            dfs(ny, nx, count + 1);
        }
        isVist[board[cy][cx] - 'A'] = false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        isVist = new boolean[26];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = s.charAt(j);
            }
        }
        dfs(0, 0, 1);
        System.out.println(maxCount);
    }

}
