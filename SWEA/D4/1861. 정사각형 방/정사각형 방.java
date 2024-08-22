import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    static int N;
    static int maxCnt;
    static int minValue;
    static final int[] dy = { 0, -1, 0, 1 };
    static final int[] dx = { -1, 0, 1, 0 };

    public static void dfs(int[][] board, int ci, int cj, int cnt, int startValue) {
        for (int d = 0; d < 4; d++) {
            int ny = ci + dy[d];
            int nx = cj + dx[d];
            // 현재 값의 +1가 같지 않다면 continue
            if (ny < 0 || ny >= N || nx < 0 || nx >= N
                    || board[ny][nx] != (board[ci][cj] + 1)) {
                continue;
            }
            dfs(board, ny, nx, cnt + 1, startValue);
        }
        // 방의 수가 같다면 시작 value가 작은 지점으로 업데이트
        if (cnt == maxCnt) {
            minValue = Math.min(minValue, startValue);
        } else if (cnt > maxCnt) {
            minValue = startValue;
            maxCnt = cnt;
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            maxCnt = 0;
            minValue = Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            int[][] board = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dfs(board, i, j, 1, board[i][j]);
                }
            }
            sb.append("#" + tc + " " + minValue + " " + maxCnt + "\n");
        }
        System.out.println(sb.toString());
    }
}