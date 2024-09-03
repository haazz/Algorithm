import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
    static int N;
    static int M;
    static int maxCost;
    static int result;
    static final int[] dy = { 1, 0, -1, 0 };
    static final int[] dx = { 0, 1, 0, -1 };

    public static int getIncome(int[][] board, int ci, int cj, int k) {
        // bfs를 활용해서 총 수익을 계산합니다.
        Queue<int[]> q = new LinkedList<>();
        int[][] visited = new int[N][N];
        int income = 0;
        q.add(new int[] { ci, cj, k - 1 });
        visited[ci][cj] = 1;

        while (!q.isEmpty()) {
            int[] elem = q.poll();
            if (board[elem[0]][elem[1]] == 1) {
                income += M;
            }
            if (elem[2] <= 0) {
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int ni = elem[0] + dy[i];
                int nj = elem[1] + dx[i];

                if (ni < 0 || ni >= N || nj < 0 || nj >= N
                        || visited[ni][nj] == 1) {

                    continue;
                }
                visited[ni][nj] = 1;
                q.add(new int[] { ni, nj, elem[2] - 1 });
            }
        }
        return income;
    }

    public static void dfs(int[][] board, int ci, int cj, int k, int cost, int income) {
        // dfs를 이용해 범위를 증가하면 완전탐색을 합니다.
        if (income - cost > 0) {
            result = Math.max(result, (Integer) (income / M));
        }
        if (maxCost < cost || k > N + 1) {
            return;
        }

        dfs(board, ci, cj, k + 1, cost + (4 * (k - 1)), getIncome(board, ci, cj, k));
    }

    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream("./sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            maxCost = M;
            result = 0;
            int[][] board = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if (board[i][j] == 1) {
                        maxCost += M;
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int[][] protect = new int[N][N];
                    protect[i][j] = 1;
                    dfs(board, i, j, 1, 0, 0);
                }
            }
            System.out.println("#" + test_case + " " + result);
        }
    }
}