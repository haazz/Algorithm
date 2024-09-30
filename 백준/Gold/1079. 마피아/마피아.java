import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int eun;
    static int maxGameCount;

    public static void dfs(int[][] board, int[] crimes, boolean[] dead, int cnt) {
        if (dead[eun]) {
            // System.out.println(cnt);
            maxGameCount = Math.max(maxGameCount, (N - cnt) / 2);
            return;
        }

        if (cnt % 2 == 0) {
            // 밤
            for (int i = 0; i < N; i++) {
                if (dead[i]) {
                    continue;
                }
                // kill i
                dead[i] = true;

                for (int j = 0; j < N; j++) {
                    crimes[j] += board[i][j];
                }

                dfs(board, crimes, dead, cnt - 1);

                for (int j = 0; j < N; j++) {
                    crimes[j] -= board[i][j];
                }
                dead[i] = false;
            }
        } else {
            // 낮
            int maxIdx = -1;
            int maxValue = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                if (!dead[i] && maxValue < crimes[i]) {
                    maxValue = crimes[i];
                    maxIdx = i;
                }
            }
            // if (maxIdx == eun) {
            // maxGameCount = Math.max(maxGameCount, (cnt / 2) + 1);
            // return;
            // }

            dead[maxIdx] = true;
            dfs(board, crimes, dead, cnt - 1);
            dead[maxIdx] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        maxGameCount = 0;

        int[] crimes = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            crimes[i] = Integer.parseInt(st.nextToken());
        }

        int[][] board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        eun = Integer.parseInt(br.readLine());

        dfs(board, crimes, new boolean[N], N);

        System.out.println(maxGameCount);

    }
}