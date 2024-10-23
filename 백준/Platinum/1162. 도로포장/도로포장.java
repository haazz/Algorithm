import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int N;
    static int M;
    static int K;
    static List<Map<Integer, Long>> graph;
    static long[][] dp;
    static boolean[][] visit;

    public static int getSmallIdx(int k) {
        long minValue = Long.MAX_VALUE;
        int minIdx = 0;

        for (int i = 0; i < N; i++) {
            if (dp[k][i] < minValue && !visit[k][i]) {
                minIdx = i;
                minValue = dp[k][i];
            }
        }
        return minIdx;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        dp = new long[K + 1][N];
        visit = new boolean[K + 1][N];

        for (int i = 0; i < N; i++) {
            for (int k = 0; k < K + 1; k++) {
                dp[k][i] = Long.MAX_VALUE;
            }

            graph.add(new HashMap<>());
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    graph.get(i).put(i, 0L);
                }
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            long dist = Long.parseLong(st.nextToken());

            if (graph.get(y).containsKey(x)) {
                dist = Math.min(graph.get(y).get(x), dist);
            }
            if (graph.get(x).containsKey(y)) {
                dist = Math.min(graph.get(x).get(y), dist);
            }
            graph.get(y).put(x, dist);
            graph.get(x).put(y, dist);
        }

        // dijk
        // for (int node : graph.get(0).keySet()) {
        // dp[0][node] = graph.get(0).get(node);
        // }

        for (int k = 0; k < K + 1; k++) {
            // visit[k][0] = true;
            dp[k][0] = 0;

            for (int i = 0; i < N - 1; i++) {

                // for (int y = 0; y < K + 1; y++) {
                // for (int x = 0; x < N; x++) {
                // System.out.print(dp[y][x] + " ");
                // }
                // System.out.println();
                // }

                int minNode = getSmallIdx(k);
                visit[k][minNode] = true;

                // System.out.println(k + " " + minNode);

                for (int node : graph.get(minNode).keySet()) {
                    if (visit[k][node]) {
                        continue;
                    }
                    if (dp[k][node] - dp[k][minNode] > graph.get(minNode).get(node)) {
                        dp[k][node] = dp[k][minNode] + graph.get(minNode).get(node);
                    }
                    if (k >= 1 && dp[k][node] > dp[k - 1][minNode]) {
                        dp[k][node] = dp[k - 1][minNode];
                    }
                }
            }
        }

        // System.out.println("");
        // for (int i = 0; i < K + 1; i++) {
        // for (int j = 0; j < N; j++) {
        // System.out.print(dp[i][j] + " ");
        // }
        // System.out.println();
        // }

        System.out.println(dp[K][N - 1]);
    }
}