import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int M;
    static int[] dp;

    public static void dfs(List<Integer>[] graph, boolean[] visit, int start, int current, int dist) {
        if (dp[current] != 0) {
            dp[start] = Math.max(dp[current] + dist - 1, dp[start]);
            return;
        }
        if (graph[current].isEmpty()) {
            dp[start] = Math.max(dist, dp[start]);
            return;
        }
        for (int i = 0; i < graph[current].size(); i++) {
            int node = graph[current].get(i);
            if (visit[node]) {
                continue;
            }
            visit[node] = true;
            dfs(graph, visit, start, node, dist + 1);
            visit[node] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        List<Integer>[] graph = new ArrayList[N + 1];
        dp = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            graph[n2].add(n1);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            dfs(graph, new boolean[N + 1], i, i, 1);
        }

        for (int i = 1; i < N + 1; i++) {
            sb.append(dp[i] + " ");
        }

        System.out.println(sb);
    }
}