class Solution {
    int N;
    int M;
    int K;
    int[] target;
    String answer = null;
    int[] dy = {1, 0, 0, -1};
    int[] dx = {0, -1, 1, 0};
    char[] dc = {'d', 'l', 'r', 'u'};
    
    public void dfs(int y, int x, String path, int depth) {
        if (Math.abs(target[0] - y) + Math.abs(target[1] - x) > K - depth) {
            return;
        }
        if (depth >= K) {
            if (target[0] == y && target[1] == x) {
                answer = path;
            }
            return;
        }
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            
            if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
                continue;
            }
            dfs(ny, nx, path + dc[d], depth + 1);
            if (answer != null) {
                return;
            }
        }
    }
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n;
        M = m;
        K = k;
        target = new int[] {r - 1, c - 1};
        if (Math.abs(target[0] - x + target[1] - y) % 2 != k % 2) {
            return "impossible";
        }
        dfs(x - 1, y - 1, "", 0);
        if (answer == null) {
            return "impossible";
        }
        return answer;
    }
}