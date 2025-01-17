import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static int N, M;
    static List<Integer>[] graph;
    static boolean[] visited;
    static int countTree = 0;
    static int countCalc = 0;

    public static void bfs(int startNode) {
        visited[startNode] = true;
        Queue<int[]> q = new LinkedList<>();
        for (int toNode : graph[startNode]) {
            q.add(new int[] { startNode, toNode });
            visited[toNode] = true;
        }

        while (!q.isEmpty()) {
            int[] node = q.poll();

            for (Integer toNode : graph[node[1]]) {
                if (toNode == node[0]) {
                    continue;
                }
                if (visited[toNode]) {
                    // System.out.println("delete: " + node[0] + " " + toNode + " " + node[1]);
                    countCalc++;
                    visited[toNode] = true;
                    graph[toNode].remove(Integer.valueOf(node[1]));
                    continue;
                }
                q.add(new int[] { node[1], toNode });
                visited[toNode] = true;
            }   
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                continue;
            }
            bfs(i);
            countTree++;
        }
        countTree--;
        // System.out.println(countTree);
        // System.out.println(countCalc);
        System.out.println(countTree + countCalc);
    }
}