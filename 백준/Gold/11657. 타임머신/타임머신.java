import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.*;

class Main {
    static int N;
    static int M;
    static Map<Integer, Integer>[] graph;
    static long[] minDist;
    static StringBuilder sb = new StringBuilder();
    static final long MAX_VALUE = Long.MAX_VALUE;

    public static boolean bell(int startNode) {
        minDist[startNode] = 0;

        // n만큼 반복
        for (int i = 0; i < N; i++) {
            // edge 만큼 반복
            for (int currentNode = 0; currentNode < N; currentNode++) {
                for (int nextNode : graph[currentNode].keySet()) {
                    if (minDist[currentNode] >= Integer.MAX_VALUE) {
                        continue;
                    }

                    long dist = graph[currentNode].get(nextNode) + minDist[currentNode];
                    if (dist < minDist[nextNode]) {
                        minDist[nextNode] = dist;
                        if (i == N - 1) {
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
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

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
            int value = Integer.parseInt(st.nextToken());

            if (graph[node1].containsKey(node2)) {
                value = Math.min(value, graph[node1].get(node2));
            }
            graph[node1].put(node2, value);
        }

        if (bell(0)) {
            sb.append("-1");
        } else {
            for (int i = 1; i < N; i++) {
                if (minDist[i] >= MAX_VALUE) {
                    sb.append("-1");
                } else {
                    sb.append(minDist[i]);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}