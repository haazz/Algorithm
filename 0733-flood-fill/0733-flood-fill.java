/*
bfs로 색상 변경 original color 들고 있기
m,n <= 50이니까 많아야 2500
*/

class Solution {
    private final int[] dy = {0, -1, 0, 1};
    private final int[] dx = {1, 0, -1, 0};

    public void bfs(int[][] image, int sy, int sx, int tc, int oc) {
        if (tc == oc) {
            return;
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {sy, sx});
        image[sy][sx] = tc;
        
        while (!q.isEmpty()) {
            int[] elem = q.poll();

            for (int d = 0; d < 4; d++) {
                int ny = elem[0] + dy[d];
                int nx = elem[1] + dx[d];

                if (ny < 0 || ny >= image.length || nx < 0 || nx >= image[0].length) {
                    continue;
                }

                if (image[ny][nx] == oc) {
                    image[ny][nx] = tc;
                    q.add(new int[] {ny, nx});
                }
            }
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        bfs(image, sr, sc, color, image[sr][sc]);
        return image;
    }
}