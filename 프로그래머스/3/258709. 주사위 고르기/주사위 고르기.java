import java.util.*;

class Solution {
    HashMap<Integer, List<Integer>> combs = new HashMap<>();
    int N;
        
    public void comb(int[][] dice, int start, int visit, int depth, int value) {
        if (depth >= N / 2) {
            if (combs.get(visit) == null) {
                combs.put(visit, new ArrayList<>());
            }
            combs.get(visit).add(value);
            return;
        }
        
        for (int i = start; i < N; i++) {
            for (int j = 0; j < 6; j++) {
                comb(dice, i + 1, visit | (1 << i), depth + 1, value + dice[i][j]);
            }
        }
    }
    
    public int bs(int target, int visit) {
        int s = 0;
        
        // System.out.println(combs.get(visit));
        int e = combs.get(visit).size() - 1;
        int res = 0;
        
        while (s <= e) {
            int mid = (s + e) / 2;
            
            if (target > combs.get(visit).get(mid)) {
                s = mid + 1;
                res = mid;
            } else {
                e = mid - 1;
            }
        }
        // System.out.println(target + " " + res);
        return res;
    }
    
    public int[] comp() {
        int resV = 0;
        int maxWin = 0;
        
        for (int visit : combs.keySet()) {
            Collections.sort(combs.get(visit));
        }
        
        for (int visit : combs.keySet()) {
            int win = 0;
            for (int val : combs.get(visit)) {
                win += bs(val, visit ^ ((1 << N) - 1));
            }
            
            // System.out.println(visit + " " +win + " "  + combs.get(visit));
            if (win > maxWin) {
                maxWin = win;
                resV = visit;
            }
        }
        int[] res = new int[N / 2];
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if ((resV & (1 << i)) != 0) {
                res[cnt++] = i + 1;
            }
        }
        return res;
    }
    
    public int[] solution(int[][] dice) {
        int[] answer = {};
        N = dice.length;
        comb(dice, 0, 0, 0, 0);
        return comp();
    }
}