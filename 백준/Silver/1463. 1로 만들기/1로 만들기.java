import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int minCnt = Integer.MAX_VALUE;

    public static void dfs(int num, int cnt) {
        // System.out.println(num + " " + cnt);
        if (minCnt < cnt) {
            return;
        }
        if (num <= 1) {
            minCnt = Math.min(cnt, minCnt);
            return;
        }
        if (num % 3 == 0) {
            dfs(num / 3, cnt + 1);
        }
        if (num % 2 == 0) {
            dfs(num / 2, cnt + 1);
        }
        dfs(num - 1, cnt + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dfs(N, 0);

        System.out.println(minCnt);
    }
}
