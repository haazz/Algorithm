import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, E, U, V;
    static Map<Integer, Integer>[] graph;

    public static int dijk(int start, int end) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[start] = 0;
        pq.add(new int[] { start, 0 });

        while (!pq.isEmpty()) {
            int[] elem = pq.poll();
            for (int node : graph[elem[0]].keySet()) {
                if (dp[node] > dp[elem[0]] + graph[elem[0]].get(node)) {
                    dp[node] = dp[elem[0]] + graph[elem[0]].get(node);
                    pq.add(new int[] { node, dp[node] });
                }
            }
        }
        return dp[end];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb;

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new Map[N];

        for (int i = 0; i < N; i++) {
            graph[i] = new HashMap<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            int value = Integer.parseInt(st.nextToken());

            if (graph[node1].get(node2) != null) {
                value = Math.min(value, graph[node1].get(node2));
            }
            graph[node1].put(node2, value);
            graph[node2].put(node1, value);
        }

        st = new StringTokenizer(br.readLine());
        U = Integer.parseInt(st.nextToken()) - 1;
        V = Integer.parseInt(st.nextToken()) - 1;

        int uv = dijk(U, V);
        int su = dijk(0, U);
        int ve = dijk(V, N - 1);
        int sv = dijk(0, V);
        int ue = dijk(U, N - 1);
        if (uv == Integer.MAX_VALUE ||
                ((su == Integer.MAX_VALUE || ve == Integer.MAX_VALUE) &&
                        (sv == Integer.MAX_VALUE || ue == Integer.MAX_VALUE))) {
            System.out.println(-1);
            return;
        }
        System.out.println(uv + Math.min(su + ve, sv + ue));
    }
}