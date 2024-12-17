import java.util.*;
import java.io.*;

public class Main {
    static int N;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        long dp[] = new long[N + 2];

        for (int i = 1; i <= N; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1]);
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int endTime = i + t;
            if (endTime > N + 1) {
                continue;
            }
            dp[endTime] = Math.max(dp[endTime], dp[i] + p);
        }
        System.out.println(Math.max(dp[N + 1], dp[N]));
    }
}