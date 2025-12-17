class Solution {
    public int maxSubArray(int[] nums) {        
        if (nums.length == 1) {
            return nums[0];
        }

        int maxv = nums[0];
        int minv = nums[0];

        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];

            maxv = Math.max(maxv, Math.max(nums[i], nums[i] - minv));
            minv = Math.min(minv, nums[i]);
        }

        return maxv;
    }
}