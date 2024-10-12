import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
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
                    if (a.charAt(i - 1) != b.charAt(j - 1))  {
                         dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                         continue;
                    }
                    dp[i][j] = dp[i - 1][j - 1] + 1;
              }
          }
          System.out.println(dp[aSize][bSize]);
     }
}