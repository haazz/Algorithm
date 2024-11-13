import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[][] apps;
    static int[][] dp;
    static int MAX_VALUE = 100001;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        apps = new int[N][2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            apps[i][0] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            apps[i][1] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N][MAX_VALUE];
        int result = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < MAX_VALUE; j++) {
                if(i == 0) {
                    if (j >= apps[i][1]) dp[i][j] = apps[i][0];
                }
                else {
                    if (j >= apps[i][1])
                        dp[i][j] = Math.max(dp[i - 1][j - apps[i][1]] + apps[i][0], dp[i - 1][j]);
                    else
                        dp[i][j] = dp[i - 1][j];
                }
                
                if (dp[i][j] >= M) {
                    result = Math.min(j, result);
                }
            }
        }
        System.out.println(result);
    }
}