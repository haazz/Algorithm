import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int cnt = 0;
    static int result = 0;
    static final int[] dy = { 0, 0, 1, 1 };
    static final int[] dx = { 0, 1, 0, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] board = new int[N + 1];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            board[i] = board[i - 1] + Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken());
            sb.append(board[e] - board[s]);
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
