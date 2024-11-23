import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Map<Integer, Integer>[] graph;

    public static int[] bfs(int startNode) {
        Queue<int[]> q = new LinkedList<>();
        boolean[] visit = new boolean[N];
        int[] maxElem = { 0, 0 };

        q.add(new int[] { startNode, 0 });
        visit[startNode] = true;

        while (!q.isEmpty()) {
            int[] elem = q.poll();

            if (elem[1] > maxElem[1]) {
                maxElem = elem;
            }
            for (int toNode : graph[elem[0]].keySet()) {
                if (visit[toNode]) {
                    continue;
                }
                visit[toNode] = true;
                q.add(new int[] { toNode, elem[1] + graph[elem[0]].get(toNode) });
            }
        }
        return maxElem;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        graph = new HashMap[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new HashMap<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            int value = Integer.parseInt(st.nextToken());

            if (graph[node1].get(node2) != null && value > graph[node1].get(node2)) {
                continue;
            }
            graph[node1].put(node2, value);
            graph[node2].put(node1, value);
        }

        int[] maxElem = bfs(0);
        maxElem = bfs(maxElem[0]);
        System.out.println(maxElem[1]);
    }
}
