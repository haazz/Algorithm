class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 0; i < amount + 1; i++) {
            dp[i] = 100000;
        }
        dp[0] = 0;

        for (int i = 0; i < amount + 1; i++) {
            for (int j = 0; j < coins.length; j++) {
                long next = (long)coins[j] + i;
                // System.out.println(next);
                if (next > amount) {
                    continue;
                }
                dp[(int)next] = Math.min(dp[i] + 1, dp[(int)next]);
            }
        }

        if (dp[amount] == 100000) {
            return -1;
        }
        return dp[amount];
    }
}