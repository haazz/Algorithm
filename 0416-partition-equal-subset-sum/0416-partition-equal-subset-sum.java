/*
누적합으로 간단하게 풀 수 있을듯?
for문 두 번만 돌면됨

-> 아 이거 이분탐색 문제다.
1. 다 더한 값의 절반이 될 수 있는 subset을 찾는다. (이분탐색으로)
2. 찾으면 true
3. 아니면 flase

-> 이분 탐색이 안되네
1. combination 구하기

-> 이분 탐색 되지 않나?

-> dp로 도달 가능한 수를 모두 체크
-> 최대 20000번만 돌면 됨
*/
class Solution {

    public boolean canPartition(int[] nums) {
        int target = 0;

        for (int i = 0; i < nums.length; i++) {
            target += nums[i];
        }
        if (target % 2 != 0) {
            return false;
        }
        target /= 2;

        Set<Integer> combs = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            Set<Integer> tmp = new HashSet<>();
            tmp.add(nums[i]);
            for (int elem : combs) {
                if (elem + nums[i] > target) {
                    continue;
                }
                if (elem + nums[i] == target) {
                    return true;
                }
                tmp.add(elem + nums[i]);
            }
            combs.addAll(tmp);
        }

        return combs.contains(target);
    }
}