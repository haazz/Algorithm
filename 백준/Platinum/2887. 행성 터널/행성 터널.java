import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    static int N;

    public static int union(int[] unionArray, int node1, int node2) {
        node1 = find(unionArray, node1);
        node2 = find(unionArray, node2);
        if (node1 < node2) {
            return unionArray[node2] = node1;
        }
        return unionArray[node1] = node2;
    }

    public static int find(int[] unionArray, int node) {
        if (unionArray[node] == node) {
            return node;
        }
        return unionArray[node] = find(unionArray, unionArray[node]);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        // int[][] planets = new int[N][3];
        int[] unionArray = new int[N];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        PriorityQueue<int[]>[] planets = new PriorityQueue[3];

        for (int i = 0; i < 3; i++) {
            planets[i] = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                planets[j].add(new int[] { i, Integer.parseInt(st.nextToken()) });
            }
            unionArray[i] = i;
        }

        for (int i = 0; i < 3; i++) {
            int[] prevNode = planets[i].poll();
            while (!planets[i].isEmpty()) {
                int[] currentNode = planets[i].poll();
                pq.add(new int[] { prevNode[0], currentNode[0], currentNode[1] - prevNode[1] });
                prevNode = currentNode;
            }
        }

        int cnt = 0;
        long dist = 0;

        while (!pq.isEmpty() && cnt < N - 1) {
            int[] elem = pq.poll();

            if (find(unionArray, elem[0]) == find(unionArray, elem[1])) {
                continue;
            }
            union(unionArray, elem[0], elem[1]);
            dist += elem[2];
            cnt++;
        }

        System.out.println(dist);
    }
}