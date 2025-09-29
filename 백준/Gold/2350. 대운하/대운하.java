import java.util.*;
import java.io.*;

class Main {
    static int[] uf;

    public static boolean union(int x, int y) {
        y = find(y);
        x = find(x);

        if (y == x) {
            return false;
        }
        uf[y] = x;
        return true;
    }

    public static int find(int x) {
        if (x == uf[x]) {
            return x;
        }
        return uf[x] = find(uf[x]);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Map<Integer, Integer>[] graph = new HashMap[N + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[2] >= b[2]) {
                return -1;
            }
            return 1;
        });
        uf = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            graph[i] = new HashMap<>();
            uf[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.add(new int[] { s, d, w });
        }

        while (!pq.isEmpty()) {
            int[] elem = pq.poll();

            if (union(elem[0], elem[1])) {
                graph[elem[0]].put(elem[1], elem[2]);
                graph[elem[1]].put(elem[0], elem[2]);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            sb.append(findMaxW(graph, new boolean[N + 1], s, d, Integer.MAX_VALUE, 0)).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static int findMaxW(Map<Integer, Integer>[] graph, boolean[] visit, int node, int d, int w, int answer) {
        if (node == d) {
            return Math.max(answer, w);
        }
        for (int nNode : graph[node].keySet()) {
            if (visit[nNode]) {
                continue;
            }
            visit[nNode] = true;
            answer = Math.max(
                    findMaxW(graph, visit, nNode, d, Math.min(graph[node].get(nNode), w), answer),
                    answer);
            visit[nNode] = false;
        }
        return answer;
    }
}