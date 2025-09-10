class Solution {
    int[] dy = new int[] {0, 1, 0, -1};
    int[] dx = new int[] {1, 0, -1, 0};

    public List<Integer> spiralOrder(int[][] matrix) {
        int d = 0;
        int y = 0;
        int x = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        int visit = -1000;
        List<Integer> answer = new ArrayList<>();
        int maxSize = N * M;

        while (answer.size() < maxSize) {
            answer.add(matrix[y][x]);
            matrix[y][x] = visit;
            y += dy[d];
            x += dx[d];

            if (y >= N || y < 0 || x < 0 || x >= M || matrix[y][x] == visit) {
                y -= dy[d];
                x -= dx[d];
                d = (d + 1) % 4;
                y += dy[d];
                x += dx[d];
            }
            
        }
        return answer;
    }
}