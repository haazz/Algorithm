import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        String b = br.readLine();
        int aSize = a.length();
        int bSize = b.length();
        int[][] dp = new int[aSize + 1][bSize + 1];

        for (int i = 1; i <= aSize; i++) {
            for (int j = 1; j <= bSize; j++) {
                if (a.charAt(i - 1) != b.charAt(j - 1)) {
                    if (dp[i][j - 1] > dp[i - 1][j]) {
                        dp[i][j] = dp[i][j - 1];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                    continue;
                }
                dp[i][j] = dp[i - 1][j - 1] + 1;
            }
        }
        System.out.println(dp[aSize][bSize]);

        int i = aSize;
        int j = bSize;
        String s = "";
        while (i > 0 && j > 0) {
            if (dp[i - 1][j] == dp[i][j]) {
                i--;
            } else if (dp[i][j - 1] == dp[i][j]) {
                j--;
            } else {
                i--;
                j--;
                s = a.charAt(i) + s;
            }
        }

        System.out.println(s);
    }
}