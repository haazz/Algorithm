import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int N;
    static int M;
    static Map<Integer, Integer>[] graph;
    static int[] dijk;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long maxValue = 0L;

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        while (!pq.isEmpty()) {
            int num = pq.poll();
            maxValue = Math.max(maxValue, num * N);
            N--;
        }
        System.out.println(maxValue);
    }
}