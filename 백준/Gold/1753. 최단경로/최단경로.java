import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.*;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int M;
    static int K;
    static List<Map<Integer, Integer>> graph;
    static final int MAX_VALUE = Integer.MAX_VALUE;

    public static int getMinNode(int[] dp, boolean[] visit) {
        int minNode = K;
        int minValue = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            if (visit[i]) {
                continue;
            }
            if (minValue > dp[i]) {
                minValue = dp[i];
                minNode = i;
            }
        }
        return minNode;
    }

    public static int[] dijkstra() {

        boolean[] visit = new boolean[N];
        int[] dp = new int[N];

        dp[K] = 0;
        visit[K] = true;

        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int node : graph.get(K).keySet()) {
            dp[node] = graph.get(K).get(node);
        }

        for (int i = 0; i < N - 1; i++) {
            int minNode = getMinNode(dp, visit);
            visit[minNode] = true;

            // for (int j = 0; j < N; j++)
            // System.out.print(dp[j] + " ");
            // System.out.println();

            for (int node : graph.get(minNode).keySet()) {
                if (visit[node]) {
                    continue;
                }

                if (graph.get(minNode).get(node) < dp[node] - dp[minNode]) {
                    dp[node] = graph.get(minNode).get(node) + dp[minNode];
                }
            }
        }
        return dp;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine()) - 1;
        graph = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            graph.add(new HashMap<>());
            graph.get(i).put(i, 0);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int dist = Integer.parseInt(st.nextToken());

            if (graph.get(y).containsKey(x)) {
                dist = Integer.min(graph.get(y).get(x), dist);
            }
            graph.get(y).put(x, dist);
        }

        int[] dp = dijkstra();

        for (int i = 0; i < N; i++) {
            if (dp[i] == Integer.MAX_VALUE) {
                sb.append("INF");
            } else {
                sb.append(dp[i]);
            }
            sb.append("\n");
        }
        System.out.print(sb);

    }
}