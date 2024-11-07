import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] dp = new int[11];
        dp[1] = 1; // 초기 값 초기화
        dp[2] = 2;
        dp[3] = 4;

        for (int j = 4; j <= 10; j++) {
            dp[j] = dp[j - 3] + dp[j - 2] + dp[j - 1];
        }

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            System.out.println(dp[num]);
        }

    }
}