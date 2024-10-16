import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R;
    static int C;
    static int N;
    static int[][] board;
    static int clustNum = 2;
    static final int[] dy = { 0, -1, 0, 1 };
    static final int[] dx = { -1, 0, 1, 0 };

    public static void setCluster(boolean[][] visit, int startY, int startX) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { startY, startX });
        visit[startY][startX] = true;

        // 클러스터 번호 매기기
        while (!q.isEmpty()) {
            int[] elem = q.poll();
            board[elem[0]][elem[1]] = clustNum;

            for (int d = 0; d < 4; d++) {
                int ny = dy[d] + elem[0];
                int nx = dx[d] + elem[1];

                if (ny < 0 || ny >= R || nx < 0 || nx >= C
                        || visit[ny][nx] || board[ny][nx] == 0) {
                    continue;
                }
                visit[ny][nx] = true;
                q.add(new int[] { ny, nx });
            }
        }
        clustNum++;
    }

    public static int positionOnGround(int startY, int startX) {
        int minFall = Integer.MAX_VALUE;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visit = new boolean[R][C];
        q.add(new int[] { startY, startX });
        visit[startY][startX] = true;

        // 클러스터 번호 매기기
        while (!q.isEmpty()) {
            int[] elem = q.poll();

            // 떨어질 수 있는 거리 업데이트
            int fall = 1;

            while (elem[0] + fall < R && (board[elem[0] + fall][elem[1]] == 0
                    || board[elem[0] + fall][elem[1]] == board[elem[0]][elem[1]])) {
                fall++;
            }
            fall--;
            minFall = Math.min(minFall, fall);

            for (int d = 0; d < 4; d++) {
                int ny = dy[d] + elem[0];
                int nx = dx[d] + elem[1];

                if (ny < 0 || ny >= R || nx < 0 || nx >= C
                        || visit[ny][nx] || board[ny][nx] == 0) {
                    continue;
                }
                visit[ny][nx] = true;
                q.add(new int[] { ny, nx });
            }
        }

        return minFall;
    }

    public static void moveToGround(int startY, int startX, int fallDist) {
        int[][] nBoard = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                nBoard[i][j] = board[i][j];
            }
        }
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visit = new boolean[R][C];
        int currentClustNum = board[startY][startX];
        q.add(new int[] { startY, startX });
        visit[startY][startX] = true;

        while (!q.isEmpty()) {
            int[] elem = q.poll();

            // 바닥으로 옮기기
            if (!(elem[0] - fallDist >= 0 && visit[elem[0] - fallDist][elem[1]])) {
                nBoard[elem[0]][elem[1]] = 0;
            }
            nBoard[elem[0] + fallDist][elem[1]] = currentClustNum;

            for (int d = 0; d < 4; d++) {
                int ny = dy[d] + elem[0];
                int nx = dx[d] + elem[1];

                if (ny < 0 || ny >= R || nx < 0 || nx >= C
                        || visit[ny][nx] || board[ny][nx] != currentClustNum) {
                    continue;
                }
                visit[ny][nx] = true;
                q.add(new int[] { ny, nx });
            }
        }

        board = nBoard;
    }

    public static void updateBoard(int stickI, int stickJ) {
        boolean[][] visit = new boolean[R][C];
        board[stickI][stickJ] = 0;
        visit[stickI][stickJ] = true;

        // 4방향 x인지 체크 후 bfs 진입
        for (int d = 0; d < 4; d++) {
            int ny = dy[d] + stickI;
            int nx = dx[d] + stickJ;

            if (ny < 0 || ny >= R || nx < 0 || nx >= C
                    || board[ny][nx] == 0 || visit[ny][nx]) {
                continue;
            }
            setCluster(visit, ny, nx);
            int fallDist = positionOnGround(ny, nx);
            if (fallDist == 0) {
                continue;
            }
            moveToGround(ny, nx, fallDist);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new int[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                char ch = line.charAt(j);
                if (ch == 'x') {
                    board[i][j] = 1;
                }
            }
        }

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int stick = R - Integer.parseInt(st.nextToken());

            // 막대 던지기
            if (i % 2 == 0) {
                for (int j = 0; j < C; j++) {
                    if (board[stick][j] != 0) {
                        updateBoard(stick, j);
                        break;
                    }
                }
            } else {
                for (int j = C - 1; j >= 0; j--) {
                    if (board[stick][j] != 0) {
                        updateBoard(stick, j);
                        break;
                    }
                }
            }

            // for (int r = 0; r < R; r++) {
            // for (int c = 0; c < C; c++) {
            // System.out.print(board[r][c]);
            // }
            // System.out.println("");
            // }
            // System.out.println("");
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == 0) {
                    System.out.print('.');
                } else {
                    System.out.print('x');
                }

            }
            System.out.println("");
        }
    }
}