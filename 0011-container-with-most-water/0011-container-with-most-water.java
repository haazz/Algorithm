class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int answer = 0;

        while (left <= right) {
            int cur = Math.min(height[left], height[right]) * (right - left);
            answer = Math.max(answer, cur);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return answer;
    }
}