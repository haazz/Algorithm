import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        
        long l = 0;
        long r = Long.MAX_VALUE;
        long answer = r;
        
        while (l <= r) {
            long mid = (l + r) / 2;
            long cnt = 0;
            
            for (int i = 0; i < times.length; i++) {
                cnt += mid / times[i];
                if (cnt > n) {
                    break;
                }
            }
            
            if (cnt >= n) {
                r = mid - 1;
                answer = mid;
            } else {
                l = mid + 1;
            }
        }
        return answer;
    }
}