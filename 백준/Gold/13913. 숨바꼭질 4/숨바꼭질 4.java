import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int MIN_RESULT = Integer.MAX_VALUE;
    static int MAX = 100000;
    static int N, K;
    static int[] dp;
    static int[] prev;

    public static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(N);
        dp[N] = 0;
        prev[N] = -1;

        while (!q.isEmpty()) {
            int node = q.poll();

            if (node * 2 <= MAX && dp[node * 2] > dp[node] + 1) {
                prev[node * 2] = node;
                dp[node * 2] = dp[node] + 1;
                q.offer(node * 2);
            }
            if (node + 1 <= MAX && dp[node + 1] > dp[node] + 1) {
                prev[node + 1] = node;
                dp[node + 1] = dp[node] + 1;
                q.offer(node + 1);
            }
            if (node - 1 >= 0 && dp[node - 1] > dp[node] + 1) {
                prev[node - 1] = node;
                dp[node - 1] = dp[node] + 1;
                q.offer(node - 1);
            }

        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[MAX + 1];
        prev = new int[MAX + 1];

        for (int i = 0; i < MAX + 1; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        bfs();
        Stack<Integer> reverseTrace = new Stack<>();
        StringBuilder sb = new StringBuilder();
        sb.append(dp[K]).append("\n");

        while (K != -1) {
            reverseTrace.push(K);
            K = prev[K];
        }
        while (!reverseTrace.isEmpty()) {
            sb.append(reverseTrace.pop()).append(" ");
        }
        System.out.println(sb);
    }
}