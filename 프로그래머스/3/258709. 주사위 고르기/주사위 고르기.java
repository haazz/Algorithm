import java.util.*;

class Solution {
    HashMap<Integer, List<Integer>> combs;
    int N;
    
    public void comb(int[][] dice, int visit, int start, int depth, int val) {
        if (depth >= N / 2) {
            if (!combs.containsKey(visit)) {
                combs.put(visit, new ArrayList<>());
            }
            combs.get(visit).add(val);
            return;
        }
        
        for (int i = start; i < N; i++) {
            for (int j = 0; j < 6; j++) {
                comb(dice, visit | (1 << i), i + 1, depth + 1, val + dice[i][j]);
            }
        }
    }
    
    public int bs(List<Integer> list, int val) {
        int answer = -1;
        int l = 0;
        int r = list.size() - 1;
        
        while (l <= r) {
            int mid = (l + r) / 2;
            
            if (list.get(mid) < val) {
                answer = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return answer;
    }
    
    public int[] solution(int[][] dice) {
        N = dice.length;
        combs = new HashMap<>();
        comb(dice, 0, 0, 0, 0);
        int maxComb = 0;
        int maxWin = 0;
        
        for (int key : combs.keySet()) {
            Collections.sort(combs.get(key));
        }
        
        for (int key : combs.keySet()) {
            int op = ((1 << N) - 1) ^ key;
            int win = 0;
            System.out.println(key + " " + op);

            for (int val : combs.get(key)) {
                win += bs(combs.get(op), val) + 1;
            }
            if (maxWin < win) {
                maxComb = key;
                maxWin = win;
            }
        }
        
        int[] answer = new int[N / 2];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            if (((1 << i) & maxComb) == 0) {
                continue;
            }
            answer[idx] = i + 1;
            idx++;
        }
        
        return answer;
    }
    
    
}