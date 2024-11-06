import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int R;
    static int Q;
    static Set<Integer>[] graph;
    static int[] dp;
    static boolean[] visit;

    public static int dfs(int node, int cnt) {
        if (graph[node].isEmpty()) {
            return dp[node] = 1;
        }

        for (int nextNode : graph[node]) {
            if (visit[nextNode]) {
                continue;
            }
            visit[nextNode] = true;
            dp[node] += dfs(nextNode, cnt + 1);
            visit[nextNode] = false;
        }
        return dp[node] += 1;
    }

    // public static int bfs(int subNode) {

    // int cnt = 1;
    // Queue<Integer> q = new LinkedList<>();
    // q.add(subNode);

    // while (!q.isEmpty()) {
    // int node = q.poll();

    // for (int nextNode : graph[node]) {
    // q.add(nextNode);
    // cnt++;
    // }
    // }
    // return cnt;
    // }

    public static void toTree(int rootNode) {
        boolean[] visit = new boolean[N];
        visit[rootNode] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(rootNode);

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int nextNode : graph[node]) {
                if (visit[nextNode]) {
                    continue;
                }
                q.add(nextNode);
                graph[nextNode].remove(node);
                visit[nextNode] = true;
            }
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        graph = new HashSet[N];
        visit = new boolean[N];
        dp = new int[N];

        for (int i = 0; i < N; i++) {
            graph[i] = new HashSet<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            graph[node1].add(node2);
            graph[node2].add(node1);
        }

        // toTree(R - 1);
        // for (int i = 0; i < N; i++)
        // System.out.println(tree[i]);
        visit[R - 1] = true;
        dp[R - 1] = dfs(R - 1, 1);

        for (int i = 0; i < Q; i++) {
            // sb.append(bfs(Integer.parseInt(br.readLine()) - 1));
            sb.append(dp[Integer.parseInt(br.readLine()) - 1]);
            sb.append("\n");
        }
        System.out.print(sb);
    }
}