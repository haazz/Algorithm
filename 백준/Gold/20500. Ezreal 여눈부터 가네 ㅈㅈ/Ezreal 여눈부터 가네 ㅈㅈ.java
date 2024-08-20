import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 2];
        dp[2] = 1;

        for (int i = 3; i <= N; i++) {
            for (int j = i - 1; j >= 1; j--) {
                dp[i] = (dp[i] + dp[j]) % 1_000_000_007;
            }
            if (i % 2 == 0)
                dp[i]++;
        }

        System.out.println(dp[N]);
    }
}