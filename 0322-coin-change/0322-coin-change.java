class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 0; i < amount + 1; i++) {
            dp[i] = 100000;
        }
        dp[0] = 0;

        for (int i = 1; i < amount + 1; i++) {
            for (int j = 0; j < coins.length; j++) {
                int prev = i - coins[j];

                if (prev < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[prev] + 1, dp[i]);
            }
        }

        if (dp[amount] == 100000) {
            return -1;
        }
        return dp[amount];
    }
}