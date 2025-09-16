class Solution {
    boolean[][] visit;
    int[] dy = {0, 1, 0, -1};
    int[] dx = {1, 0, -1, 0};
    int N;
    int M;

    public boolean dfs(char[][] board, String word, int y, int x, int idx) {
        if (idx >= word.length()) {
            return true;
        }
        
        for (int d = 0; d < 4; d++) {
            int ny = dy[d] + y;
            int nx = dx[d] + x;

            if (ny < 0 || ny >= N || nx < 0 || nx >= M || 
                visit[ny][nx] || board[ny][nx] != word.charAt(idx)) {
                continue;
            }
            visit[ny][nx] = true;
            if (dfs(board, word, ny, nx, idx + 1)) {
                visit[ny][nx] = false;
                return true;
            }
            visit[ny][nx] = false;
        }
        return false;
    }

    public boolean exist(char[][] board, String word) {
        N = board.length;
        M = board[0].length;
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (word.charAt(0) != board[i][j]) {
                    continue;
                }
                visit[i][j] = true;
                if (dfs(board, word, i, j, 1)) {
                    return true;
                }
                visit[i][j] = false;
            }
        }
        return false;
    }
}