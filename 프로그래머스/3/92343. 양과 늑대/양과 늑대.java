import java.util.*;

class Solution {
    List<List<Integer>> tree = new ArrayList<>();
    int N;
    int answer = 0;
    
    public void sol(int[] info, List<Integer> canGo, int sheep, int wolf, int cur) {
        if (info[cur] == 0) {
            sheep++;
        } else {
            wolf++;
        }
        if (sheep <= wolf) {
            return;
        }
        answer = Math.max(sheep, answer);
        canGo.remove(Integer.valueOf(cur));
        for (int next: tree.get(cur)) {
            canGo.add(next);
        }
        for (int next: canGo) {
            sol(info, new ArrayList<>(canGo), sheep, wolf, next);
        }
    }
    
    public int solution(int[] info, int[][] edges) {
        N = info.length;
        
        for (int i = 0; i < N; i++) {
            tree.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            tree.get(edges[i][0]).add(edges[i][1]);
        }
        sol(info, new ArrayList<>(), 0, 0, 0);
        return answer;
    }
}