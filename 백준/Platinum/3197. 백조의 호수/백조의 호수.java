import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R;
    static int C;
    static Queue<int[]> meltList = new LinkedList<>();
    static Queue<int[]> moveSwan = new LinkedList<>();
    static final int[] dy = {0, 1, 0, -1};
    static final int[] dx = {1, 0, -1, 0};

    public static int bfs(int[][] cntBoard, int startY, int startX) {
        boolean[][] visit = new boolean[R][C];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        pq.add(new int[] {startY, startX, 0});
        visit[startY][startX] = true;

        while (!pq.isEmpty()) {
            int[] elem = pq.poll();
            for (int d = 0; d < 4; d++) {
                int ny = elem[0] + dy[d];
                int nx = elem[1] + dx[d];
                
                if (ny < 0 || ny >= R || nx < 0 || nx >= C
                || visit[ny][nx]) {
                    continue;
                }
                if (cntBoard[ny][nx] == -1) {
                    return elem[2];
                }
                pq.add(new int[]{ny, nx, Math.max(elem[2], cntBoard[ny][nx])});
                visit[ny][nx] = true;
            }
        }
        return -1;    
    }

    public static boolean isSwanConnect(char[][] board, boolean[][] visit) {
        Queue<int[]> nextQueue = new LinkedList<>();
        while (!moveSwan.isEmpty()) {
            int[] elem = moveSwan.poll();
            visit[elem[0]][elem[1]] = true;

            for (int d = 0; d < 4; d++) {
                int ny = elem[0] + dy[d];
                int nx = elem[1] + dx[d];
                
                if (ny < 0 || ny >= R || nx < 0 || nx >= C
                || visit[ny][nx]) {
                    continue;
                }
                visit[ny][nx] = true;
                if (board[ny][nx] == 'L') {
                    return true;
                }
                if (board[ny][nx] == 'X') {
                    nextQueue.add(new int[] {ny, nx});
                    continue;
                }
                moveSwan.add(new int[]{ny, nx});
            }
        }
        moveSwan = nextQueue;
        return false;
    }

    public static void melt(char[][] board) {
        int meltSize = meltList.size();

        while (meltSize-- > 0) {
            int[] pos = meltList.poll();
            for (int d = 0; d < 4; d++) {
                int ny = pos[0] + dy[d];
                int nx = pos[1] + dx[d];

                if (ny < 0 || ny >= R || nx < 0 || nx >= C) {
                    continue;
                }
                if (board[ny][nx] == 'X') {
                    board[ny][nx] = '.';
                    meltList.add(new int[] {ny, nx});
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int cnt = 0;

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        char[][] board = new char[R][C];
        boolean[][] visit = new boolean[R][C];
        
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = s.charAt(j);
                if (board[i][j] == 'L') {
                    moveSwan.add(new int[] {i, j});
                    meltList.add(new int[] {i, j});
                } else if (board[i][j] == '.') {
                    meltList.add(new int[] {i, j});
                }
            }
        }
        moveSwan.poll();
        visit[moveSwan.peek()[0]][moveSwan.peek()[1]] = true;
        while (!isSwanConnect(board, visit)) {
            melt(board);
            // for (int i = 0; i < R; i++) {
            //     for (int j = 0; j < C; j++) {
            //         System.out.print(board[i][j] + " ");
            //     }
            //     System.out.println("");
            // }
            cnt++;
        }
        // int[][] cntBoard = melt(board);
        // for (int i = 0; i < R; i++) {
        //     for (int j = 0; j < C; j++) {
        //         System.out.print(cntBoard[i][j] + " ");
        //     }
        //     System.out.println("");
        // }
        // cnt = bfs(cntBoard, swan.get(0)[0], swan.get(0)[1]);

        System.out.println(cnt);
    }
}