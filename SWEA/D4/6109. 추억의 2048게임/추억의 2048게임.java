import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Solution
 */
public class Solution {
    static int N;
    static final int[] dy = { 1, -1, 0, 0 };
    static final int[] dx = { 0, 0, 1, -1 };

    public static void move(int[][] board, int ci, int cj, int d) {
        Queue<Integer> list = new LinkedList<>();
        int prev = 0;
        int ny = ci;
        int nx = cj;

        // 리스트에 최종 숫자 타일들을 저장합니다.
        while (ny < N && ny >= 0 && nx < N && nx >= 0) {
            if (board[ny][nx] != 0) {
                if (prev == 0) {
                    prev = board[ny][nx];
                } else if (board[ny][nx] == prev) {
                    list.add(prev * 2);
                    prev = 0;
                } else {
                    list.add(prev);
                    prev = board[ny][nx];
                }
                // 보드를 미리 0으로 초기화 해 놓습니다.
                board[ny][nx] = 0;
            }

            ny += dy[d];
            nx += dx[d];
        }

        // 리스트에 담은 타일들을 앞에서부터 넣습니다.
        while (!list.isEmpty()) {
            board[ci][cj] = list.poll();
            ci += dy[d];
            cj += dx[d];
        }

        if (prev != 0) {
            board[ci][cj] = prev;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            String op = st.nextToken();
            int[][] board = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            switch (op) {
                case "up":
                    for (int i = 0; i < N; i++) {
                        move(board, 0, i, 0);
                    }
                    break;
                case "down":
                    for (int i = 0; i < N; i++) {
                        move(board, N - 1, i, 1);
                    }
                    break;
                case "left":
                    for (int i = 0; i < N; i++) {
                        move(board, i, 0, 2);
                    }
                    break;
                case "right":
                    for (int i = 0; i < N; i++) {
                        move(board, i, N - 1, 3);
                    }
                    break;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("#" + tc + "\n");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sb.append(board[i][j] + " ");
                }
                sb.append("\n");
            }
            System.out.print(sb);

        }
    }
}