import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Main
 */
public class Main {
    static int N;
    static int M;
    static int[][] graph;

    public static void bfs(int start) {
        Queue<int[]> q = new LinkedList<>();
        boolean[] visit = new boolean[N];
        q.add(new int[] { start, 0 });
        visit[start] = true;

        while (!q.isEmpty()) {
            int[] elem = q.poll();

            System.out.println(start + ": " + elem[0] + " " + elem[1]);
            for (int i = 0; i < N; i++) {
                if (graph[elem[0]][i] == Integer.MAX_VALUE || visit[i]) {
                    continue;
                }
                q.add(new int[] { i, elem[1] + 1 });
                graph[start][i] = elem[1];
                visit[i] = true;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int person1 = Integer.parseInt(st.nextToken()) - 1;
            int person2 = Integer.parseInt(st.nextToken()) - 1;
            graph[person1][person2] = 1;
            graph[person2][person1] = 1;

        }

        // for (int i = 0; i < N; i++) {
        // bfs(i);
        // }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (graph[j][i] < graph[j][k] - graph[i][k]) {
                        graph[j][k] = graph[j][i] + graph[i][k];
                    }
                }
            }
        }
        int minBaconIndex = 0;
        int minBaconValue = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int baconValue = 0;
            for (int j = 0; j < N; j++) {
                baconValue += graph[i][j];
            }
            if (baconValue < minBaconValue) {
                minBaconIndex = i;
                minBaconValue = baconValue;
            }
        }

        System.out.println(minBaconIndex + 1);

    }
}