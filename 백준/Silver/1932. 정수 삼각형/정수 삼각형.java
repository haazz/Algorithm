import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i < N; i++) {
            board[i][0] += board[i - 1][0];
            for (int j = 1; j <= i; j++) {
                board[i][j] += Math.max(board[i - 1][j - 1], board[i - 1][j]);
            }
        }

        int result = 0;

        for (int i = 0; i < N; i++) {
            result = Math.max(board[N - 1][i], result);
        }

        System.out.println(result);
    }
}
