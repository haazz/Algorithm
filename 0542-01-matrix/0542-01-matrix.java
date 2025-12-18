class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};
        
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    q.add(new int[] {i, j, 0});
                } else {
                    mat[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] elem = q.poll();
            
            for (int d = 0; d < 4; d++) {
                int ny = dy[d] + elem[0];
                int nx = dx[d] + elem[1];

                if (ny < 0 || ny >= n || nx < 0 || nx >= m || mat[ny][nx] == 0 || mat[ny][nx] != Integer.MAX_VALUE) {
                    continue;
                }
                mat[ny][nx] = elem[2] + 1;
                q.add(new int[] {ny, nx, elem[2] + 1});
            }
            
        }

        return mat;
    }
}