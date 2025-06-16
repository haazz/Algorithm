// n log n 까지는 괜찮다 -> bs * for문

class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        int left = 0;
        int right = 200000000;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (check(stones, mid, k)) {
                left = mid + 1;
                answer = mid;
            } else {
                right = mid - 1;
            }
        }
        return answer;
    }
    
    public boolean check(int[] stones, int mid, int k) {
        int cnt = 0;
        
        for (int stone : stones) {
            if (mid <= stone) {
                cnt = 0;
            } else {
                cnt++;
            }
            
            if (cnt >= k) {
                return false;
            }
        }
        return true;
    }
}