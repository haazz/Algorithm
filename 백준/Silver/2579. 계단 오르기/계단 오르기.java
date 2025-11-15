
// 다이나믹 프로그래밍으로 풀기
// 연속 밟기에 조심해서 풀기 2차원 [3][N]으로 풀 수 있지 않을까?
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] points = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            points[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[N + 1][3];

        for (int i = 1; i <= N; i++) {
            dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = dp[i - 1][0] + points[i];
            dp[i][2] = Math.max(dp[i - 1][1] + points[i], dp[i][1]);
        }

        System.out.println(dp[N][2]);
    }
}