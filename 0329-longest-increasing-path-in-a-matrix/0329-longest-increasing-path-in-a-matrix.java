class Solution {
    int N;
    int M;
    int[][] dp;
    int[] dy = {0, 1, 0, -1};
    int[] dx = {1, 0, -1, 0};
    int res = 1;

    public void bfs(int[][] matrix, int sy, int sx) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {sy, sx, 1});
        dp[sy][sx] = 1;

        while (!q.isEmpty()) {
            int[] elem = q.poll();
            
            for (int d = 0; d < 4; d++) {
                int ny = elem[0] + dy[d];
                int nx = elem[1] + dx[d];
                
                if (ny < 0 || ny >= N || nx < 0 || nx >= M || 
                    matrix[ny][nx] <= matrix[elem[0]][elem[1]] || 
                    dp[ny][nx] >= elem[2] + 1) {
                    continue;
                }
                dp[ny][nx] = elem[2] + 1;
                res = Math.max(res, dp[ny][nx]);
                q.add(new int[] {ny, nx, elem[2] + 1});
            }
        }

    }

    public int longestIncreasingPath(int[][] matrix) {
        N = matrix.length;
        M = matrix[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dp[i][j] >= 1) {
                    continue;
                }
                bfs(matrix, i, j);
            }
        }

        // while (!pq.isEmpty()) {
        //     int[] elem = pq.poll();
        //     if (dp[elem[0]][elem[1]] >= 1) {
        //         continue;
        //     }
        //     bfs(matrix, elem[0], elem[1]);
        // }

        return res;
    }
}