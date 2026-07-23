class Solution {
    int N;
    String[][] dp;

    public int comp(String a, String b) {
        
        for (int i = 1; i < a.length(); i++) {
            if (b.startsWith(a.substring(i))) {
                return a.length() - i;
            }
        }
        return 0;
    }

    public String shortestSuperstring(String[] words) {
        N = words.length;
        int[][] graph = new int[N][N];
        int fVisit = (1 << N) - 1;
        dp = new String[N][fVisit + 1];

        for (int i = 0; i < N; i++ ){
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    continue;
                }
                graph[i][j] = comp(words[i], words[j]);
            }
        }

        for (int i = 0; i < N; i++) {
            dp[i][1 << i] = words[i];
        }
        
        for (int i = 1; i < fVisit + 1; i++) {
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) == 0) {
                    continue;
                }
                for (int k = 0; k < N; k++) {
                    if (j == k || (i & (1 << k)) != 0) {
                        continue;
                    }
                    String ns = dp[j][i] + words[k].substring(graph[j][k]);
                    if (dp[k][i | (1 << k)] != null && dp[k][i | (1 << k)].length() <= ns.length()) {
                        continue;
                    }
                    dp[k][i | (1 << k)] = ns;
                }
            }
        }

        // for (int i = 0; i < N; i++) {
        //     for (int j = 0; j < fVisit; j++) {
        //         System.out.print(dp[i][j] + '\t');

        //     }
        //     System.out.println("");
        // }

        for (int i = 1; i < N; i++) {
            if (dp[i - 1][fVisit].length() < dp[i][fVisit].length()) {
                dp[i][fVisit] = dp[i - 1][fVisit];
            }
        }

        return dp[N - 1][fVisit];
    }
}