class Solution {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            if (!hm.containsKey(nums[i])) {
                hm.put(nums[i], 1);
            } else {
                hm.put(nums[i], hm.get(nums[i]) + 1);
            }
        }

        int target = nums.length % 2 == 1 ? nums.length / 2 + 1 : nums.length / 2;

        for (int key : hm.keySet()) {
            if (hm.get(key) >= target) {
                return key;
            }
        }
        return nums[0];
    }
}