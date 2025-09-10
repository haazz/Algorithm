class Solution {
    public boolean canPartition(int[] nums) {
        int target  = 0;

        for (int i = 0; i < nums.length; i++) {
            target += nums[i];
        }
        if (target % 2 == 1) {
            return false;
        }
        target /= 2;

        boolean[][] dp = new boolean[nums.length + 1][target + 1];
        dp[0][0] = true;

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= target; j++) {
                
                if (dp[i - 1][j]) {
                    dp[i][j] = true;
                }
                
                if (j + nums[i - 1] > target) {
                    continue;
                }
                if (dp[i - 1][j]) {
                    dp[i][j + nums[i - 1]] = true;
                }
            }
        }
        // for (int i = 0; i < nums.length +1 ; i++) {
        //     for (int j = 0; j < target + 1; j++) {
        //         System.out.print(dp[i][j] + " ");
        //     }
        //     System.out.println("");
        // }
        return dp[nums.length][target];
    }
}