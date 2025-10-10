import java.util.*;

class Solution {
    final int[] dy = new int[] {0, 1, 0, -1};
    final int[] dx = new int[] {1, 0, -1, 0};
    int N;
    int M;
    int answer = 0;
    
    public int game(int[][] board, int[] pos, int turn) {
        int y = pos[(turn % 2) * 2];
        int x = pos[(turn % 2) * 2 + 1];
        if (board[y][x] == 0) {
            return turn;
        }
        
        int min = Integer.MAX_VALUE;
        int max = 0;
        
        board[y][x] = 0;
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            
            if (ny < 0 || ny >= N || nx < 0 || nx >= M || board[ny][nx] == 0) {
                continue;
            }
            pos[(turn % 2) * 2] = ny;
            pos[(turn % 2) * 2 + 1] = nx;
            int res = game(board, pos, turn + 1);
            if (res % 2 != turn % 2) {
                min = Math.min(res, min);
            } else {
                max = Math.max(res, max);
            }
        }
        pos[(turn % 2) * 2] = y;
        pos[(turn % 2) * 2 + 1] = x;
        board[y][x] = 1;
        if (min == Integer.MAX_VALUE && max == 0) {
            return turn;
        }
        if (min == Integer.MAX_VALUE) {
            return max;
        }
        return min;
    }
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        N = board.length;
        M = board[0].length;
        return game(board, new int[] {aloc[0], aloc[1], bloc[0], bloc[1]}, 0);
    }
}