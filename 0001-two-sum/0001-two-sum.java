/*
2중 for문 100,000,000
Map에 담아서 contains 확인?
*/

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (m.get(target - nums[i]) != null) {
                return new int[] {m.get(target - nums[i]), i};
            }
            m.put(nums[i], i);
        }
        return null;
    }
}