import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int V;
    static int E;
    static int[] uf;

    public static void union(int node1, int node2) {
        node1 = find(node1);
        node2 = find(node2);
        uf[node1] = node2;
    }

    public static int find(int node) {
        if (uf[node] == node) {
            return node;
        }
        return uf[node] = find(uf[node]);
    }
    

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        uf = new int[V];

        for (int i = 0; i < V; i++) {
            uf[i] = i;
        }
        
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            int value = Integer.parseInt(st.nextToken());
            pq.add(new int[] { node1, node2, value });
        }

        int result = 0;

        while (!pq.isEmpty()) {
            int[] elem = pq.poll();

            if (find(elem[0]) == find(elem[1])) {
                continue;
            }
            union(elem[0], elem[1]);
            result += elem[2];
        }
        System.out.println(result);

    }
}