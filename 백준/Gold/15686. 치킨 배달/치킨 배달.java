import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int chickenRestSize;
    static int homeSize;
    static int result = Integer.MAX_VALUE;
    static final int[] dy = { 0, -1, 0, 1 };
    static final int[] dx = { -1, 0, 1, 0 };

    public static void dfs(int[][] chickenDist, boolean[] close, int closeCnt, int start) {
        if (closeCnt <= M) {
            int totalDist = 0;
            for (int i = 0; i < homeSize; i++) {
                int minDist = Integer.MAX_VALUE;
                for (int j = 0; j < chickenRestSize; j++) {
                    if (close[j]) {
                        continue;
                    }
                    minDist = Math.min(minDist, chickenDist[j][i]);
                }
                totalDist += minDist;
            }

            result = Math.min(totalDist, result);
            return;
        }

        for (int i = start; i < chickenRestSize; i++) {
            close[i] = true;
            dfs(chickenDist, close, closeCnt - 1, i + 1);
            close[i] = false;
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] board = new int[N][N];
        List<int[]> rest = new ArrayList<>();
        List<int[]> home = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 2) {
                    rest.add(new int[] { i, j });
                    chickenRestSize++;
                } else if (board[i][j] == 1) {
                    home.add(new int[] { i, j });
                    homeSize++;
                }
            }
        }

        int[][] chickenDist = new int[chickenRestSize][homeSize];

        for (int i = 0; i < chickenRestSize; i++) {
            for (int j = 0; j < homeSize; j++) {
                int distance = Math.abs(rest.get(i)[0] - home.get(j)[0]) + Math.abs(rest.get(i)[1] - home.get(j)[1]);
                chickenDist[i][j] = distance;
                // System.out.print(distance + " ");
            }
            // System.out.println();
        }

        dfs(chickenDist, new boolean[chickenRestSize], chickenRestSize, 0);

        System.out.println(result);
    }
}