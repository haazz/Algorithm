import java.util.*;

class Solution {
    int[] answer;
    int remArrow = 0;
    int maxsub = 0;
    
    public void sol(int arrow, int[] info, int ap, int rp, int idx, int[] used) {
        if (idx < 0) {
            if (maxsub < rp - ap) {
                answer = used.clone();
                maxsub = rp - ap;
                remArrow = arrow;
            }
            return;
        }
        if (info[idx] < arrow) {
            used[idx] = info[idx] + 1;
            if (info[idx] != 0) {
                sol(arrow - info[idx] - 1, info, ap - (10 - idx), rp + (10 - idx), idx - 1, used);
            } else {
                sol(arrow - info[idx] - 1, info, ap, rp + (10 - idx), idx - 1, used);
            }
            used[idx] = 0;
        }
        sol(arrow, info, ap, rp, idx - 1, used);
    }
    
    public int[] solution(int n, int[] info) {
        int ap = 0;
        for (int i = 0; i < info.length; i++) {
            if (info[i] > 0) {
                ap += 10 - i;
            }
        }
        sol(n, info, ap, 0, 9, new int[11]);
        
        if (answer == null) {
            return new int[] {-1};
        }
        answer[10] = remArrow;
        return answer;
    }
}