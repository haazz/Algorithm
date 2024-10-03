import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int X;

    public static int dijkstra(List<int[]>[]graph, int start, int end) {
        int dijk[] = new int[N + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        for (int i = 0; i < N + 1; i++) {
            dijk[i] = Integer.MAX_VALUE;
        }
        dijk[start] = 0;
        pq.add(new int[] { start, 0 });

        while (!pq.isEmpty()) {
            int[] elem = pq.poll();

            if (dijk[elem[0]] < elem[1]) {
                continue;
            }

            for (int[] node : graph[elem[0]]) {
                int dist = elem[1] + node[1];
                if (dijk[node[0]] < dist) {
                    continue;
                }
                dijk[node[0]] = dist;
                pq.add(new int[] { node[0], dist });
            }
        }
        return dijk[end];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int result = 0;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        
        List<int[]>[] graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            graph[Integer.parseInt(st.nextToken())]
                .add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        

        for (int i = 1; i < N + 1; i++) {
            int distance = dijkstra(graph, i, X);
            distance += dijkstra(graph, X, i);
            result = Math.max(result, distance);
        }

        System.out.println(result);
    }
}
