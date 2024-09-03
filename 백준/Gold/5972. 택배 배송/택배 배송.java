import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        List<int[]>[] graph = new ArrayList[N + 1];
        int[] distance = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            distance[i] = Integer.MAX_VALUE; // 무한대로 초기화
            graph[i] = new ArrayList<int[]>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            graph[a].add(new int[] { b, value });
            graph[b].add(new int[] { a, value });
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        pq.add(new int[] { 0, 1 });
        distance[1] = 0;

        while (!pq.isEmpty()) {
            int dist = pq.peek()[0];
            int node = pq.poll()[1];

            if (dist > distance[node]) {
                continue;
            }
            for (int[] n : graph[node]) {
                int nDist = n[1] + dist;
                if (nDist < distance[n[0]]) {
                    distance[n[0]] = nDist;
                    pq.add(new int[] { nDist, n[0] });
                }
            }
        }

        System.out.println(distance[N]);
    }
}
