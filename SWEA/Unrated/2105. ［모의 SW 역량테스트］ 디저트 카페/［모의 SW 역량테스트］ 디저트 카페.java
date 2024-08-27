import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Solution {
    // 탐색 순서에 맞춰 dy, dx를 설계했습니다.
    static int[] dy = { 1, 1, -1, -1 };
    static int[] dx = { 1, -1, -1, 1 };
    static int maxCnt;
    static int N;

    public static boolean isVisited(int cc, Stack<Integer> visited) {
        // 이미 먹은 디저트인지 확인했습니다.
        for (int c : visited) {
            if (c == cc) {
                return true;
            }
        }
        return false;
    }

    public static void dfs(int[][] board, Stack<Integer> visited, int sy, int sx, int cy, int cx, int dir, int cnt) {
        // 시작 지점에 다시 방문하는 시점에 cnt 값을 업데이트 하였습니다.
        if (cy == sy && cx == sx) {

            if (maxCnt < cnt) {
                maxCnt = cnt;
            }
            return;
        }
        if (cy < 0 || cy >= N || cx < 0 || cx >= N ||
                isVisited(board[cy][cx], visited)) {
            return;
        }
        visited.push(board[cy][cx]);
        // 방향을 바꾸지 않고 직진하는 경우의 dfs입니다.
        dfs(board, visited, sy, sx, cy + dy[dir], cx + dx[dir], dir, cnt + 1);
        if (dir < 3) {
            // 방향을 바꾸는 경우의 dfs입니다.
            // dir이 2일 때까지만 방향을 바꿀 수 있어 if문으로 처리하였습니다.
            dfs(board, visited, sy, sx, cy + dy[dir + 1], cx + dx[dir + 1], dir + 1, cnt + 1);
        }
        visited.pop();
    }

    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            maxCnt = -1;
            int[][] board = new int[N][N];
            Stack<Integer> visited = new Stack<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    visited.push(board[i][j]);
                    dfs(board, visited, i, j, i + dy[0], j + dx[0], 0, 1);
                    visited.pop();
                }
            }
            System.out.printf("#%d %d\n", test_case, maxCnt);
        }
    }
}