import java.util.*;

class Solution {
    int answer = 0;
    int N;
    List<Integer>[] graph;
    
    
    public void find(int[] info, List<Integer> list, int node, int sheep, int wolf) {
        if (info[node] == 0) {
            sheep++;
        } else {
            wolf++;
        }
        if (sheep <= wolf) {
            return;
        }
        answer = Math.max(sheep, answer);
        list.remove(new Integer(node));
        list.addAll(graph[node]);
        for (int nNode : list) {
            find(info, new ArrayList<>(list), nNode, sheep, wolf);
        }
    }
    
    public int solution(int[] info, int[][] edges) {
        N = info.length;
        graph = new ArrayList[N];
        
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            graph[edges[i][0]].add(edges[i][1]);
        }
        
        // 찾기
        find(info, new ArrayList<>(), 0, 0, 0);
        return answer;
    }
}