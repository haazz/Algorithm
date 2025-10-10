// 시간 복잡도 30 ^ 5?
import java.util.*;

class Solution {
    HashMap<Integer, List<Integer>> hm = new HashMap<>();
    
    public void comb(int[][] dice, int start, int value, int visit, int depth) {
        if (depth >= dice.length / 2) {
            if (!hm.containsKey(visit)) {
                hm.put(visit, new ArrayList<>());
            }
            hm.get(visit).add(value);
            return;
        }
        
        for (int i = start; i < dice.length; i++) {
            for (int j = 0; j < 6; j++) {
                comb(dice, i + 1, value + dice[i][j], visit | 1 << i, depth + 1);
            }
        }
    }
    
    public int calcWin(List<Integer> l1, List<Integer> l2) {
        int win = 0;
        
        for (int d1: l1) {
            win += bs(d1, l2) + 1;
        }
        return win;
    }
    
    public int bs(int target, List<Integer> ls) {
        int l = 0;
        int r = ls.size() - 1;
        int answer = -1;
        
        while (l <= r) {
            int mid = (l + r) / 2;
            
            if (ls.get(mid) < target) {
                answer = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return answer;
    }
    
    
    
    public int[] solution(int[][] dice) {
        comb(dice, 0, 0, 0, 0);
        int[] answer = new int[dice.length / 2];
        int maxWin = 0;
        for (int key: hm.keySet()) {
            List<Integer> l1 = hm.get(key);
            List<Integer> l2 = hm.get(key ^ ((1 << dice.length) - 1));
            Collections.sort(l1);
            Collections.sort(l2);
            int win = calcWin(l1, l2);
            if (maxWin < win) {
                int idx = 0;
                for (int i = 0; i < dice.length; i++) {
                    if ((key & (1 << i)) != 0) {
                        answer[idx] = i + 1;
                        idx++;
                    }
                }
                maxWin = win;
            }
        }
        return answer;
    }
}