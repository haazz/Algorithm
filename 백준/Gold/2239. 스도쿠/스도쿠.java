import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    static final int N = 9;
    static int[][] board;
    static boolean[][] visitRow;
    static boolean[][] visitCol;
    static boolean[][][] visitSquare;

    public static boolean dfs(int start) {

        while (start < N * N && board[start / N][start % N] != 0) {
            start++;
        }

        if (start >= N * N) {
            return true;
        }

        int y = start / N;
        int x = start % N;
        for (int num = 1; num <= N; num++) {
            if (board[y][x] != 0 || visitCol[y][num] || visitRow[x][num] || visitSquare[y / 3][x / 3][num]) {
                continue;
            }
            board[y][x] = num;
            visitCol[y][num] = true;
            visitRow[x][num] = true;
            visitSquare[y / 3][x / 3][num] = true;
            if (dfs(start + 1)) {
                return true;
            }
            board[y][x] = 0;
            visitCol[y][num] = false;
            visitRow[x][num] = false;
            visitSquare[y / 3][x / 3][num] = false;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        board = new int[N][N];
        visitRow = new boolean[N][N + 1];
        visitCol = new boolean[N][N + 1];
        visitSquare = new boolean[3][3][N + 1];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = s.charAt(j) - '0';
                if (board[i][j] != 0) {
                    visitCol[i][board[i][j]] = true;
                    visitRow[j][board[i][j]] = true;
                    visitSquare[(i / 3)][(j / 3)][board[i][j]] = true;
                }
            }
        }

        dfs(0);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}