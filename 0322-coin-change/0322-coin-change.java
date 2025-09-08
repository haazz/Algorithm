class Solution {
    public int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length + 1][amount + 1];

        for (int i = 0; i < coins.length + 1; i++) {
            for (int j = 0; j < amount + 1; j++) {
                dp[i][j] = 100000;
            }
        }
        dp[0][0] = 0;

        for (int i = 0; i < coins.length; i++) {
            int k = 0;
            while (k * coins[i] <= amount) {
                for (int j = 0; j < amount + 1; j++) {
                    int next = k * coins[i] + j;
                    if (next > amount) {
                        continue;
                    }
                    dp[i + 1][next] = Math.min(dp[i + 1][next], Math.min(dp[i][next], dp[i][j] + k));
                }
                k++;
            }
        }

        // for (int i = 0; i < coins.length + 1; i++) {
        //     for (int j = 0; j < amount + 1; j++) {
        //         System.out.print(dp[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        if (dp[coins.length][amount] == 100000) {
            return -1;
        }
        return dp[coins.length][amount];
    }
}