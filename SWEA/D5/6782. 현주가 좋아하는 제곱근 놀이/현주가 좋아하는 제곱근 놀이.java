import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

class Solution {

    public static long dfs(long n, long cnt) {
        if (n == 2) {
            return cnt;
        }

        double sqrtNum = Math.sqrt(n);

        if (sqrtNum % 1 == 0) {
            return dfs((long) sqrtNum, cnt + 1);
        }
        // 다음 제곱근 가능한 수로 변경
        long nNum = (long) (sqrtNum + 1) * (long) (sqrtNum + 1);
        // 증가한 만큼 cnt 증가
        return dfs(nNum, cnt + nNum - n);
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            long n = sc.nextLong();
            System.out.println("#" + tc + " " + dfs(n, 0));
        }
    }
}
