import java.util.*;

class Solution {
    int[] uf;
    HashMap<Integer, Integer>[] graph;
    boolean[] isSum;
    int[] answer = new int[2];
    
    public int find(int x) {
        if (uf[x] == x) {
            return x;
        }
        return uf[x] = find(uf[x]);
    }
    
    public boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) {
            return false;
        }
        uf[y] = x;
        return true;
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        graph = new HashMap[n + 1];
        uf = new int[n + 1];
        isSum = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            uf[i] = i;
            graph[i] = new HashMap<>();
        }
        
        for (int i = 0; i < summits.length; i++) {
            isSum[summits[i]] = true;
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[2] <= b[2]) {
                return -1;
            }
            return 1;
        });

        for (int i = 0; i < paths.length; i++) {
            pq.add(new int[] {paths[i][0], paths[i][1], paths[i][2]});
        }
        
        while (!pq.isEmpty()) {
            int[] elem = pq.poll();
            if (union(elem[0], elem[1])) {
                graph[elem[0]].put(elem[1], elem[2]);
                graph[elem[1]].put(elem[0], elem[2]);
            }
        }
        
        answer[1] = Integer.MAX_VALUE;
        for (int i = 0; i < gates.length; i++) {
            getMin(gates[i], 0, new boolean[n + 1]);
        }
        return answer;
    }
    
    public void getMin(int node, int minVal, boolean[] visit) {
        if (minVal > answer[1]) {
            return;
        }
        if (isSum[node]) {
            if (answer[1] == minVal && answer[0] > node) {
                answer[0] = node;
            } else if (answer[1] > minVal) {
                answer[0] = node;
                answer[1] = minVal;
            }
            return;
        }
        visit[node] = true;
        
        for (int next : graph[node].keySet()) {
            if (visit[next]) {
                continue;
            }
            int time = Math.max(graph[node].get(next), minVal);
            getMin(next, time, visit);
        }
        visit[node] = false;
    }
}