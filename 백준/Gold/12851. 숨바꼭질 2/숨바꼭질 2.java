import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int min = Integer.MAX_VALUE;
    static int n, k;
    static boolean[] visited;
    static int max = 100000;
    static int minCnt = 0;

    public static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { n, 0 });

        while (!q.isEmpty()) {
            int[] node = q.poll();
            visited[node[0]] = true;
            if (node[0] == k) {
                if (min == node[1]) {
                    minCnt++;
                } else if (min > node[1]) {
                    min = node[1];
                    minCnt = 1;
                }
            }

            if (node[0] * 2 <= max && visited[node[0] * 2] == false)
                q.offer(new int[] { node[0] * 2, node[1] + 1 });
            if (node[0] + 1 <= max && visited[node[0] + 1] == false)
                q.offer(new int[] { node[0] + 1, node[1] + 1 });
            if (node[0] - 1 >= 0 && visited[node[0] - 1] == false)
                q.offer(new int[] { node[0] - 1, node[1] + 1 });
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        visited = new boolean[max + 1];
        bfs();
        System.out.println(min);
        System.out.println(minCnt);
    }
}