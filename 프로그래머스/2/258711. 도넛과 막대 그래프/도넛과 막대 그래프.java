import java.util.*;

class Solution {
    HashMap<Integer, List<Integer>> graph;
    int[] result = new int[4];
    boolean[] visit;
    
    public int dfs(int node) {
        if (graph.get(node) == null) {
            return 2;
        }
        if (graph.get(node).size() >= 2) {
            return 3;
        }
        for (int nNode : graph.get(node)) {
            if (visit[nNode]) {
                return 1;
            }
            visit[nNode] = true;
            return dfs(nNode);
        }
        return 2;
    }
    
    public int[] solution(int[][] edges) {
        graph = new HashMap<>();
        Set<Integer> targetNodes = new HashSet<>();
        int N = edges.length;
        
        // 생성한 정점 구하기
        for (int i = 0; i < N; i++) {
            int a = edges[i][0];
            int b = edges[i][1];

            if (graph.get(a) == null) {
                graph.put(a, new ArrayList<>());
            }
            targetNodes.add(b);
            graph.get(a).add(b);
        }
        
        // 새로 생긴 노드 구하기
        for (int node : graph.keySet()) {
            if (!targetNodes.contains(node) && graph.get(node).size() >= 2) {
                result[0] = node;
                break;
            }
        }
        
        // 탐색
        visit = new boolean[1000001];
        for (int node: graph.get(result[0])) {
            int type = dfs(node);
            result[type]++;
        }
        
        return result;
    }
}