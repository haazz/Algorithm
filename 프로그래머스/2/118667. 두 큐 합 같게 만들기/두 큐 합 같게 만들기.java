import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long sum1 = 0;
        long sum2 = 0;
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        
        for (int i = 0; i < queue1.length; i++) {
            sum1 += queue1[i];
            sum2 += queue2[i];
            q1.add(queue1[i]);
            q2.add(queue2[i]);
        }
        if ((sum1 + sum2) % 2 == 1) {
            return -1;
        }
        
        int maxRot = queue1.length * 4;
        while (sum1 != sum2) {
            if (maxRot < 0) {
                return -1;
            }
            
            if (sum1 > sum2) {
                int val = q1.poll();
                sum1 -= val;
                sum2 += val;
                q2.add(val);
            } else {
                int val = q2.poll();
                sum2 -= val;
                sum1 += val;
                q1.add(val);
            }
            maxRot--;
        }
        return queue1.length * 4 - maxRot;
    }
}