import java.io.*;
import java.util.*;

public class Main {
    static int[] uf;

    public static int find(int x) {
        if (uf[x] == x) {
            return x;
        }
        return uf[x] = find(uf[x]);
    }

    public static boolean union(int y, int x) {
        x = find(x);
        y = find(y);
        if (x == y) {
            return false;
        }
        uf[y] = x;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[2] <= b[2]) {
                return -1;
            }
            return 1;
        });

        uf = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            uf[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()) });
        }

        int answer = 0;
        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            if (union(edge[0], edge[1])) {
                answer += edge[2];
            }
        }

        System.out.println(answer);
    }
}