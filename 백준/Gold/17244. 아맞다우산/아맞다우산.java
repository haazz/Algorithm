import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, countThings;
    static int[][] board;
    static final int[] dy = {0, 0, 1, -1};
    static final int[] dx = {1, -1, 0, 0};
    static int[][] distance;
    static int answer = Integer.MAX_VALUE;

    public static void dfs(boolean[] visited, int prev, int dist, int depth) {
        if (depth + 1 >= countThings) {
            // System.out.println(dist + distance[prev][1]);
            answer = Math.min(answer, dist + distance[prev][1]);
            return;
        }
        for (int i = 3; i < countThings; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            dfs(visited, i, dist + distance[prev][i], depth + 1);
            visited[i] = false;
        }
    }

    public static void bfs(int sy, int sx) {
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sy, sx, 0});
        visited[sy][sx] = true;

        while (!q.isEmpty()) {
            int[] elem = q.poll();
            
            for (int d = 0; d < 4; d++) {
                int ny = elem[0] + dy[d];
                int nx = elem[1] + dx[d];

                if (ny < 0 || ny >= N || nx < 0 || nx >= M 
                    || visited[ny][nx] || board[ny][nx] == -1) {
                    continue;
                }
                if (board[ny][nx] >= 2) {
                    distance[board[sy][sx]][board[ny][nx]] = elem[2] + 1;
                    distance[board[ny][nx]][board[sy][sx]] = elem[2] + 1;
                }
                visited[ny][nx] = true;
                q.add(new int[] {ny, nx, elem[2] + 1});
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        countThings = 3;
        
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                if (line.charAt(j) == '#') {
                    board[i][j] = -1;
                } else if (line.charAt(j) == '.') {
                    board[i][j] = 0;
                } else if (line.charAt(j) == 'X') {
                    board[i][j] = countThings;
                    countThings++;
                } else if (line.charAt(j) == 'S') {
                    board[i][j] = 2;
                } else if (line.charAt(j) == 'E') {
                    board[i][j] = 1;
                }
            }
        }
        distance = new int[countThings][countThings];
        for (int i = 0; i < countThings; i++) {
            for (int j = 0; j < countThings; j++) {
                if (i == j) {
                    continue;
                }
                distance[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] >= 1) {
                    bfs(i, j);
                }
            }
        }
        boolean[] visited = new boolean[countThings];
        visited[2] = true;
        dfs(visited, 2, 0, 2);


        // for (int i = 0; i < countThings; i++) {
        //     for (int j = 0; j < countThings; j++) {
        //         System.out.print(distance[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        System.out.println(answer);

    }
}