import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int N;
    static int M;
    static Map<Integer, Integer>[] graph;
    static int[] dijk;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                if (i > 0 && j > 0) {
                    board[i][j] += board[i - 1][j];
                    board[i][j] += board[i][j - 1];
                    board[i][j] -= board[i - 1][j - 1];
                } else if (i > 0) {
                    board[i][j] += board[i - 1][j];
                } else if (j > 0) {
                    board[i][j] += board[i][j - 1];
                }
                // System.out.print(board[i][j] + " ");
            }
            // System.out.println();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int sy = Integer.parseInt(st.nextToken()) - 1;
            int sx = Integer.parseInt(st.nextToken()) - 1;
            int ey = Integer.parseInt(st.nextToken()) - 1;
            int ex = Integer.parseInt(st.nextToken()) - 1;

            int result = board[ey][ex];

            if (sy == 0 && sx != 0) {
                result -= board[ey][sx - 1];
            } else if (sy != 0 && sx == 0) {
                result -= board[sy - 1][ex];
            } else if (sy != 0 && sx != 0) {
                result -= board[ey][sx - 1];
                result -= board[sy - 1][ex];
                result += board[sy - 1][sx - 1];
            }

            sb.append(result);
            sb.append("\n");
        }

        System.out.print(sb);
    }
}