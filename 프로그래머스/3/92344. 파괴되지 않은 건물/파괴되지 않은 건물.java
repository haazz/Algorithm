class Solution {
    int N;
    int M;
    public int solution(int[][] board, int[][] skill) {
        N = board.length;
        M = board[0].length;
        int[][] dp = new int[N][M];
        
        for (int i = 0; i < skill.length; i++) {
            if (skill[i][0] == 1) {
                skill[i][5] *= -1;
            }
            dp[skill[i][1]][skill[i][2]] += skill[i][5];
            if (skill[i][3] + 1 < N) {
                dp[skill[i][3] + 1][skill[i][2]] += skill[i][5] * -1;
            }
            if (skill[i][4] + 1 < M) {
                dp[skill[i][1]][skill[i][4] + 1] += skill[i][5] * -1;
            }
            if (skill[i][3] + 1 < N && skill[i][4] + 1 < M) {
                dp[skill[i][3] + 1][skill[i][4] + 1] += skill[i][5];
            }
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dp[i][j] += dp[i][j - 1];
            }
        }
        
        int answer = 0;
        for (int j = 0; j < M; j++) {
            for (int i = 1; i < N; i++) {
                dp[i][j] += dp[i - 1][j];
                  
            }
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] + dp[i][j] >= 1) {
                    answer++;
                }
            }
            System.out.println();
        }
        return answer;
    }
}