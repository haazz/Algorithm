import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[][] board;
    static boolean[][][] visit;
    static int[] dy = { 0, -1, 0, 1 };
    static int[] dx = { -1, 0, 1, 0 };

    public static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        if (board[0][0] == 1) {
            visit[0][0][1] = true;
            q.add(new int[] { 0, 0, 1, 1 });
        } else {
            visit[0][0][0] = true;
            q.add(new int[] { 0, 0, 0, 1 });
        }

        while (!q.isEmpty()) {
            int[] elem = q.poll();

            if (elem[0] == N - 1 && elem[1] == M - 1) {
                return elem[3];
            }

            for (int d = 0; d < 4; d++) {
                int ny = elem[0] + dy[d];
                int nx = elem[1] + dx[d];

                if (ny < 0 || ny >= N || nx < 0 || nx >= M || visit[ny][nx][elem[2]]) {
                    continue;
                }
                if (board[ny][nx] == 1 && elem[2] == 1) {
                    continue;
                }

                if (board[ny][nx] == 1) {
                    visit[ny][nx][1] = true;
                    q.add(new int[] { ny, nx, 1, elem[3] + 1 });
                } else {
                    visit[ny][nx][elem[2]] = true;
                    q.add(new int[] { ny, nx, elem[2], elem[3] + 1 });
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visit = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = s.charAt(j) - '0';
            }
        }
        System.out.println(bfs());
    }
}