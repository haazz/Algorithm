
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int N;
    static int M;
    static final int[] dy = {0, 1, 0, -1};
    static final int[] dx = {1, 0, -1, 0};

    static class Island {

        List<int[]> pos;
        int idx;
        int unionParent;

        public Island(List<int[]> pos, int idx, int unionParent) {
            this.pos = pos;
            this.idx = idx;
            this.unionParent = unionParent;
        }
    }
    
    static class Bridge implements Comparable<Bridge> {
        int node1;
        int node2;
        int value;
        
        public Bridge(int node1, int node2, int value) {
            this.node1 = node1;
            this.node2 = node2;
            this.value = value;
        }

        @Override
        public int compareTo(Bridge o) {
            return this.value - o.value;
        }
    }

    public static void islandBfs(int[][] board, int ci, int cj, int cnt) {
        Queue<int[]> q = new LinkedList<>();

        board[ci][cj] = cnt;
        q.add(new int[] { ci, cj });
        while (!q.isEmpty()) {
            int[] elem = q.poll();

            for (int d = 0; d < 4; d++) {
                int ny = dy[d] + elem[0];
                int nx = dx[d] + elem[1];

                if (ny < 0 || ny >= N || nx < 0 || nx >= M
                        || board[ny][nx] == 0 || board[ny][nx] == cnt) {
                    continue;
                }
                board[ny][nx] = cnt;
                q.add(new int[] { ny, nx });
            }
        }
    }
    

    public static void bridgeBfs(int[][] board, PriorityQueue<Bridge> pq, int ci, int cj) {

        for (int d = 0; d < 4; d++) {
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[] { ci, cj, 0 });
            while (!q.isEmpty()) {
                int[] elem = q.poll();
                int ny = dy[d] + elem[0];
                int nx = dx[d] + elem[1];

                if (ny >= N || ny < 0 || nx >= M || nx < 0
                    || board[ny][nx] == board[ci][cj]) {
                    break;
                }
                if (board[ny][nx] != 0) {
                    if (elem[2] > 1) {
                        pq.add(new Bridge(board[ci][cj], board[ny][nx], elem[2]));
                    }
                    break;
                }
                q.add(new int[] { ny, nx, elem[2] + 1 });
                
                
            }
        }
    }

    public static void getBridges(int[][] board, PriorityQueue<Bridge> pq) {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] != 0) {
                    bridgeBfs(board, pq, i, j);
                }
                
            }
        }
    }

    public static int getIslands(int[][] board, int cnt) {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == -1) {
                    islandBfs(board, i, j, cnt);
                    cnt++;
                }

            }
        }
        return cnt;
    }

    public static int getParent(int[] union, int x) {
        if (x == union[x])
            return x;
        return union[x] = getParent(union, union[x]);
    }

    public static void unionParent(int[] union, int x, int y) {
        y = getParent(union, y);
        x = getParent(union, x);

        if (y < x)
            union[x] = y;
        else
            union[y] = x;
    }
    
    public static int getMinBridge(PriorityQueue<Bridge> pq, int islandsCnt) {
        int[] union = new int[islandsCnt];
        int result = 0;

        for (int i = 0; i < islandsCnt; i++) {
            union[i] = i;
        }

        while (!pq.isEmpty()) {
            Bridge b = pq.poll();
            if (getParent(union, b.node1) != getParent(union, b.node2)) {
                result += b.value;
                unionParent(union, b.node1, b.node2);
            }
        }
        
        for (int i = 1; i < islandsCnt - 1; i++) {
            if (getParent(union, i) != getParent(union, i + 1)) {
                return -1;
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] board = new int[N][M];
        PriorityQueue<Bridge> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = -1 * Integer.parseInt(st.nextToken());
            }
        }
        
        int islandsCnt = getIslands(board, 1);
        getBridges(board, pq);
        
        // for (int i = 0; i < N; i++) {
        //     for (int j = 0; j < M; j++) {
        //         System.out.print(board[i][j] + " ");
        //     }
        //     System.out.println("");
        // }

        // pq.forEach(x -> {
        //     System.out.println("[" + x.node1 + " " + x.node2 + " " + x.value + "]");
        // });

        
        System.out.println(getMinBridge(pq, islandsCnt));

    }
}
