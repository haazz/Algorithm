import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int N;
    static int M;
    static Map<Integer, Integer>[] graph;
    static int[] dijk;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new HashMap[N];
        dijk = new int[N];

        for (int i = 0; i < N; i++) {
            graph[i] = new HashMap<>();
            dijk[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            int value = Integer.parseInt(st.nextToken());

            if (graph[node1].containsKey(node2)) {
                value = Math.min(value, graph[node1].get(node2));
            }
            graph[node1].put(node2, value);
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()) - 1;
        int end = Integer.parseInt(st.nextToken()) - 1;

        // dijkstra
        boolean[] visit = new boolean[N];
        dijk[start] = 0;
        visit[start] = true;
        for (int node2 : graph[start].keySet()) {
            dijk[node2] = graph[start].get(node2);
        }

        for (int i = 0; i < N; i++) {

            int minValue = Integer.MAX_VALUE;
            int minIdx = 0;

            for (int j = 0; j < N; j++) {
                if (!visit[j] && minValue > dijk[j]) {
                    minIdx = j;
                    minValue = dijk[j];
                }
            }

            for (int node2 : graph[minIdx].keySet()) {
                if (!visit[node2] && dijk[minIdx] + graph[minIdx].get(node2) < dijk[node2]) {
                    dijk[node2] = dijk[minIdx] + graph[minIdx].get(node2);
                }
            }
            visit[minIdx] = true;
        }

        // for (int i = 0; i < N; i++) {
        // System.out.print(dijk[i] + " ");
        // }
        // System.out.println();
        System.out.println(dijk[end]);

    }
}