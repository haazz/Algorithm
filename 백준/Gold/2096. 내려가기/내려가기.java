import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int N;
    static int maxValue = Integer.MIN_VALUE;
    static int minValue = Integer.MAX_VALUE;
    static int[] dx = { -1, 0, 1 };
    static int[][] mindp;
    static int[][] maxdp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][3];
        mindp = new int[N][3];
        maxdp = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                mindp[i][j] = board[i][j];
                maxdp[i][j] = board[i][j];
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                int minValue = Integer.MAX_VALUE;
                int maxValue = Integer.MIN_VALUE;

                for (int d = 0; d < 3; d++) {
                    int nx = j + dx[d];

                    if (nx < 0 || nx >= 3) {
                        continue;
                    }
                    minValue = Math.min(minValue, mindp[i - 1][nx]);
                    maxValue = Math.max(maxValue, maxdp[i - 1][nx]);
                }
                mindp[i][j] += minValue;
                maxdp[i][j] += maxValue;
            }
        }

        for (int i = 1; i < 3; i++) {
            maxdp[N - 1][0] = Math.max(maxdp[N - 1][0], maxdp[N - 1][i]);
            mindp[N - 1][0] = Math.min(mindp[N - 1][0], mindp[N - 1][i]);
        }

        System.out.println(maxdp[N - 1][0] + " " + mindp[N - 1][0]);
        

    }
}