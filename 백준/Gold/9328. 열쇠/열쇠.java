import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int T, N, M;
    static boolean[] keys;
    static char[][] board;
    static ArrayList<int[]>[] gates;
    static boolean[][] visit;
    static int[] dy = { 0, 1, 0, -1 };
    static int[] dx = { 1, 0, -1, 0 };
    static StringBuilder sb = new StringBuilder();;

    public static void print() {
        sb.append("\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }

        sb.append("\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(visit[i][j] + "\t");
            }
            sb.append("\n");
        }
        sb.append("\n");
    }

    public static int bfs(int starty, int startx) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { starty, startx });
        // visit[starty][startx] = true;
        int cnt = 0;

        while (!q.isEmpty()) {
            int[] elem = q.poll();
            visit[elem[0]][elem[1]] = true;
            if (board[elem[0]][elem[1]] == '$') {
                cnt++;
            } else if (board[elem[0]][elem[1]] >= 'A' && board[elem[0]][elem[1]] <= 'Z') {
                if (!keys[board[elem[0]][elem[1]] - 'A']) {
                    gates[board[elem[0]][elem[1]] - 'A'].add(new int[] { elem[0], elem[1] });
                    continue;
                }
            } else if (board[elem[0]][elem[1]] >= 'a' && board[elem[0]][elem[1]] <= 'z') {
                keys[board[elem[0]][elem[1]] - 'a'] = true;
                for (int[] pos : gates[board[elem[0]][elem[1]] - 'a']) {
                    q.add(pos);
                }
            }

            for (int d = 0; d < 4; d++) {
                int ny = dy[d] + elem[0];
                int nx = dx[d] + elem[1];

                if (ny < 0 || ny >= N || nx < 0 || nx >= M ||
                        visit[ny][nx] || board[ny][nx] == '*') {
                    continue;
                }
                visit[ny][nx] = true;
                q.add(new int[] { ny, nx });
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            board = new char[N][M];
            keys = new boolean[26];
            gates = new ArrayList[26];
            visit = new boolean[N][M];
            int cnt = 0;

            for (int i = 0; i < 26; i++) {
                gates[i] = new ArrayList<>();
            }

            for (int i = 0; i < N; i++) {
                String s = br.readLine();
                for (int j = 0; j < M; j++) {
                    board[i][j] = s.charAt(j);
                }
            }
            String s = br.readLine();
            if (!"0".equals(s)) {
                for (int i = 0; i < s.length(); i++) {
                    keys[s.charAt(i) - 'a'] = true;
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if ((i == 0 || j == 0 || i == N - 1 || j == M - 1) && !visit[i][j] && board[i][j] != '*') {
                        cnt += bfs(i, j);
                    }
                }
            }

            sb.append(cnt).append("\n");
        }
        System.out.print(sb);
    }
}