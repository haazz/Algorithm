import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] board;
    static int sharkSize = 2;
    static int eatCnt = 0;
    static int cy;
    static int cx;
    static int time = 0;
    static int[] dy = { -1, 0, 0, 1 };
    static int[] dx = { 0, -1, 1, 0 };

    public static boolean bfs() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visit = new boolean[N][N];
        q.add(new int[] { cy, cx, 0 });
        // 찾았을 때 depth값을 저장
        int searchDepth = Integer.MAX_VALUE;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        while (!q.isEmpty()) {
            int[] elem = q.poll();

            for (int d = 0; d < 4; d++) {
                int ny = elem[0] + dy[d];
                int nx = elem[1] + dx[d];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N || visit[ny][nx]
                        || board[ny][nx] > sharkSize || searchDepth < elem[2]) {
                    continue;
                }

                if (board[ny][nx] > 0 && sharkSize > board[ny][nx]) {
                    searchDepth = elem[2];
                    pq.add(new int[] { ny, nx, elem[2] + 1 });
                    continue;
                }
                visit[ny][nx] = true;
                q.add(new int[] { ny, nx, elem[2] + 1 });
            }
        }

        if (!pq.isEmpty()) {
            int[] elem = pq.poll();
            board[elem[0]][elem[1]] = 0;
            eatCnt++;
            if (eatCnt >= sharkSize) {
                sharkSize++;
                eatCnt = 0;
            }
            cy = elem[0];
            cx = elem[1];
            time += elem[2];
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 9) {
                    cy = i;
                    cx = j;
                    board[i][j] = 0;
                }
            }
        }

        while (bfs())
            ;

        System.out.println(time);
    }
}
