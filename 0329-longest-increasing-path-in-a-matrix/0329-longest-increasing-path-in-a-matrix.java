class Solution {
    int N;
    int M;
    int[][] dp;
    int[] dy = {0, 1, 0, -1};
    int[] dx = {1, 0, -1, 0};
    int res = 1;

    public int dfs(int[][] matrix, int y, int x) {
        if (dp[y][x] != 0) {
            return dp[y][x];
        }

        int m = 0;

        for (int d = 0; d < 4; d++) {
            int ny = dy[d] + y;
            int nx = dx[d] + x;
            if (ny < 0 || ny >= N || nx < 0 || nx >= M || matrix[ny][nx] >= matrix[y][x]) {
                continue;
            }
            m = Math.max(m, dfs(matrix, ny, nx));
        }
        if (m == 0) {
            return 1;
        }
        return dp[y][x] = m + 1;
    }

    public int longestIncreasingPath(int[][] matrix) {
        N = matrix.length;
        M = matrix[0].length;
        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dp[i][j] >= 1) {
                    continue;
                }
                res = Math.max(res, dfs(matrix, i, j));
            }
        }

        return res;
    }
}