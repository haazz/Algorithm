import java.util.*;

class Solution {
    HashMap<Integer, List<Integer>> graph = new HashMap<>();
    HashMap<Integer, List<Integer>> rev = new HashMap<>();
    
    public int sol(int node, Set<Integer> visit) {
        if (graph.get(node).isEmpty()) {
            return 2;
        }
        if (graph.get(node).size() >= 2) {
            return 3;
        }
        if (visit.contains(node)) {
            return 1;
        }
        visit.add(node);
        for (int nNode: graph.get(node)) {
            int res = sol(nNode, visit);
            if (res >= 1) {
                return res;
            }
        }
        visit.remove(node);
        return 1;
    }
    
    public int[] solution(int[][] edges) {
        for (int i = 0; i < edges.length; i++) {
            if (!graph.containsKey(edges[i][0])) {
                graph.put(edges[i][0], new ArrayList<>());
            }
            if (!graph.containsKey(edges[i][1])) {
                graph.put(edges[i][1], new ArrayList<>());
            }
            graph.get(edges[i][0]).add(edges[i][1]);
            
            if (!rev.containsKey(edges[i][0])) {
                rev.put(edges[i][0], new ArrayList<>());
            }
            if (!rev.containsKey(edges[i][1])) {
                rev.put(edges[i][1], new ArrayList<>());
            }
            rev.get(edges[i][1]).add(edges[i][0]);
        }
        
        int[] answer = new int[4];
        for (int key : rev.keySet()) {
            if (rev.get(key).isEmpty() && graph.get(key).size() >= 2) {
                answer[0] = key;
                break;
            }
        }
        
        for (int node : graph.get(answer[0])) {
            int idx = sol(node, new HashSet<>());
            answer[idx]++;
        }
        
        return answer;
    }
}