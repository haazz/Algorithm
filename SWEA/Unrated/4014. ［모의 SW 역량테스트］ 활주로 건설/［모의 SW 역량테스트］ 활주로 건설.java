import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int X;
    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            board = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int canCnt = 0;
            // 가로
            for (int i = 0; i < N; i++) {
                int prev = board[i][0];
                int prevCnt = 1;
                int nextCnt = 0;
                boolean canGo = true;
                for (int j = 1; j < N; j++) {
                    if (Math.abs(board[i][j] - prev) > 1) {
                        canGo = false;
                        break;
                    }
                    if (board[i][j] == prev) {
                        if (nextCnt > 0) {
                            nextCnt--;
                        } else {
                            prevCnt++;
                        }
                    } else if (board[i][j] > prev) {
                        if (X > prevCnt || nextCnt > 0) {
                            canGo = false;
                            break;
                        }
                        prevCnt = 1;
                        prev = board[i][j];
                    } else {
                        if (nextCnt > 0) {
                            canGo = false;
                            break;
                        }
                        prevCnt = 0;
                        nextCnt = X - 1;
                        prev = board[i][j];
                    }
                }
                // System.out.println(i + " " + nextCnt + " " + canGo);
                if (canGo && nextCnt <= 0) {
                    canCnt++;
                }
            }

            // 세로
            for (int i = 0; i < N; i++) {
                int prev = board[0][i];
                int prevCnt = 1;
                int nextCnt = 0;
                boolean canGo = true;
                for (int j = 1; j < N; j++) {

                    if (Math.abs(board[j][i] - prev) > 1) {
                        canGo = false;
                        break;
                    }
                    if (board[j][i] == prev) {
                        if (nextCnt > 0) {
                            nextCnt--;
                        } else {
                            prevCnt++;
                        }
                    } else if (board[j][i] > prev) {
                        if (X > prevCnt || nextCnt > 0) {
                            // if (i == 2) {
                            // System.out.println(prevCnt + " " + nextCnt);
                            // }
                            canGo = false;
                            break;
                        }
                        prevCnt = 1;
                        prev = board[j][i];
                    } else {
                        if (nextCnt > 0) {
                            canGo = false;
                            break;
                        }
                        prevCnt = 0;
                        nextCnt = X - 1;
                        prev = board[j][i];
                    }
                }
                // System.out.println(i + " " + nextCnt + " " + canGo);
                if (canGo && nextCnt <= 0) {
                    canCnt++;
                }
            }
            sb.append("#");
            sb.append(tc);
            sb.append(" ");
            sb.append(canCnt);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
