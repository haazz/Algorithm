import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Main {
    static int N;
    static long maxDist = 0;
    static int maxNode = 0;

    public static void dfs(Map<Integer, Integer>[] tree, boolean[] visit, int currentNode, long dist) {
        if (dist > maxDist) {
            maxDist = dist;
            maxNode = currentNode;
        }

        for (int node : tree[currentNode].keySet()) {
            if (visit[node]) {
                continue;
            }
            visit[node] = true;
            dfs(tree, visit, node, dist + tree[currentNode].get(node));
            visit[node] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Map<Integer, Integer>[] tree = new HashMap[N];

        for (int i = 0; i < N; i++) {
            tree[i] = new HashMap<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            while (node2 != -2) {
                int value = Integer.parseInt(st.nextToken());
                tree[node1].put(node2, value);

                node2 = Integer.parseInt(st.nextToken()) - 1;
            }
        }

        // 임의의 노드에서 최장 거리인 노드를 구한다.
        boolean[] visit = new boolean[N];
        visit[0] = true;
        dfs(tree, visit, 0, 0);

        // 최장 거리인 노드를 기준으로 최장 거리를 구한다.
        visit = new boolean[N];
        visit[maxNode] = true;
        dfs(tree, visit, maxNode, 0);

        System.out.println(maxDist);
    }
}