import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.*;

class Main {
    static int N;
    static int M;
    static int W;
    static Map<Integer, Long>[] graph;
    static long[] minDist;
    static final long MAX_VALUE = Integer.MAX_VALUE;
    static StringBuilder sb = new StringBuilder();

    public static boolean bell(int startNode) {
        minDist[startNode] = 0;

        for (int i = 0; i < N; i++) {
            for (int node1 = 0; node1 < N; node1++) {
                for (int node2 : graph[node1].keySet()) {
                    long nDist = graph[node1].get(node2) + minDist[node1];
                    if (minDist[node2] > nDist) {
                        minDist[node2] = nDist;

                        if (i == (N - 1)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            graph = new HashMap[N];
            minDist = new long[N];

            for (int i = 0; i < N; i++) {
                graph[i] = new HashMap<>();
                minDist[i] = MAX_VALUE;
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int node1 = Integer.parseInt(st.nextToken()) - 1;
                int node2 = Integer.parseInt(st.nextToken()) - 1;
                long value = Long.parseLong(st.nextToken());

                if (graph[node1].containsKey(node2)) {
                    value = Math.min(value, graph[node1].get(node2));
                }
                graph[node1].put(node2, value);
                graph[node2].put(node1, value);
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int node1 = Integer.parseInt(st.nextToken()) - 1;
                int node2 = Integer.parseInt(st.nextToken()) - 1;
                long value = -1 * Long.parseLong(st.nextToken());

                if (graph[node1].containsKey(node2)) {
                    value = Math.min(value, graph[node1].get(node2));
                }
                graph[node1].put(node2, value);
            }

            if (bell(0)) {
                sb.append("YES");
            } else {
                sb.append("NO");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}