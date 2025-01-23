import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static int N, M;
    static char[][] board;
    static boolean[][] visited;
    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = { 0, 0, -1, 1 };

    public static void drop() {
        // System.out.println("drop");
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                char originChar = board[i][j];
                board[i][j] = '.';
                int ny = i + 1;

                while (ny < N && board[ny][j] == '.') {
                    ny++;
                }
                board[ny - 1][j] = originChar;
            }
        }
    }

    public static boolean bfsWithDelete(int starty, int startx) {

        Queue<int[]> q = new LinkedList<>();
        Queue<int[]> deleteList = new LinkedList<>();
        q.add(new int[] { starty, startx });
        deleteList.add(new int[] { starty, startx });
        visited[starty][startx] = true;

        while (!q.isEmpty()) {
            int[] pos = q.poll();

            for (int d = 0; d < 4; d++) {
                int ny = pos[0] + dy[d];
                int nx = pos[1] + dx[d];

                if (ny < 0 || ny >= N || nx < 0 || nx >= M || visited[ny][nx]
                        || board[ny][nx] != board[starty][startx]) {
                    continue;
                }
                q.add(new int[] { ny, nx });
                deleteList.add(new int[] { ny, nx });
                visited[ny][nx] = true;
            }
        }

        if (deleteList.size() < 4) {
            return false;
        }
        while (!deleteList.isEmpty()) {
            int[] pos = deleteList.poll();
            board[pos[0]][pos[1]] = '.';
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = 12;
        M = 6;
        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        boolean isDeleted = true;
        int count = 0;

        while (isDeleted) {
            isDeleted = false;
            visited = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] != '.' && !visited[i][j]) {
                        if (bfsWithDelete(i, j)) {
                            isDeleted = true;
                        }
                    }
                }
            }
            if (isDeleted) {
                drop();
                count++;
            }
        }

        // for (int i = 0; i < N; i++) {
        // for (int j = 0; j < M; j++) {
        // System.out.print(board[i][j]);
        // }
        // System.out.println();
        // }
        System.out.println(count);
    }
}