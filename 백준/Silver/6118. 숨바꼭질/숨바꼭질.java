import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static List<Integer>[] graph;
    static int[] result;

    public static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        q.add(new int[] { 1, 0 });
        visited[1] = true;

        while (!q.isEmpty()) {
            int[] elem = q.poll();

            if (result[1] == elem[1]) {
                result[0] = Math.min(result[0], elem[0]);
                result[2]++;
            }
            if (result[1] < elem[1]) {
                result[0] = elem[0];
                result[1] = elem[1];
                result[2] = 1;
            }

            for (int node : graph[elem[0]]) {
                if (visited[node]) {
                    continue;
                }
                visited[node] = true;
                q.add(new int[] { node, elem[1] + 1 });
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        result = new int[] { 1, 0, 1 };

        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        bfs();

        for (int i = 0; i < 3; i++) {
            System.out.print(result[i] + " ");
        }

    }
}