import java.util.*;

class Solution {
    int answer;
    int talp = 0;
    int tcop = 0;
    
    
    public int solution(int alp, int cop, int[][] problems) {
        Arrays.sort(problems, (a, b) -> {
            return a[0] - b[0] + a[1] - b[1];
        });
        talp = alp;
        tcop = cop;
        for (int i = 0; i < problems.length; i++) {
            talp = Math.max(problems[i][0], talp);
            tcop = Math.max(problems[i][1], tcop);
        }
        int[][] dp = new int[talp + 1][tcop + 1];
        
        for (int i = alp; i <= talp; i++) {
            for (int j = cop; j <= tcop; j++) {
                dp[i][j] = i + j - alp - cop;
            }
        }
        
        for (int i = alp; i <= talp; i++) {
            for (int j = cop; j <= tcop; j++) {
                if (i + 1 <= talp) {
                    dp[i + 1][j] = Math.min(dp[i][j] + 1, dp[i + 1][j]);
                }
                if (j + 1 <= tcop) {
                    dp[i][j + 1] = Math.min(dp[i][j] + 1, dp[i][j + 1]);
                }
                for (int k = 0; k < problems.length; k++) {
                    
                    if (i < problems[k][0] || j < problems[k][1]) {
                        continue;
                    }
                    int ni = Math.min(i + problems[k][2], talp);
                    int nj = Math.min(j + problems[k][3], tcop);
                    dp[ni][nj] = Math.min(dp[i][j] + problems[k][4], dp[ni][nj]);
                }
            }
        }
//         System.out.println(talp + " " + tcop);
        
        
//         for (int i = alp; i <= talp; i++) {
//             for (int j = cop; j <= tcop; j++) {
//                 System.out.print(dp[i][j] + " ");
//             }
//             System.out.println();
//         }
        
        return dp[talp][tcop];
    }
}