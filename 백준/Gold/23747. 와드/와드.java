import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
    static int R, C;
    static char[][] map;
    static int[][] resultMap;
    static final int[] dy = { -1, 1, 0, 0 };
    static final int[] dx = { 0, 0, -1, 1 };

    public static void bfs(int y, int x) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { y, x });
        resultMap[y][x] = 2;
        char keyCh = map[y][x];

        while (!q.isEmpty()) {
            int[] pos = q.poll();

            for (int d = 0; d < 4; d++) {
                int ny = dy[d] + pos[0];
                int nx = dx[d] + pos[1];

                if (ny < 0 || nx < 0 || ny >= R || nx >= C || resultMap[ny][nx] == 2 || map[ny][nx] != keyCh) {
                    continue;
                }
                resultMap[ny][nx] = 2;
                q.add(new int[] { ny, nx });
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        resultMap = new int[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken()) - 1;
        int x = Integer.parseInt(st.nextToken()) - 1;

        String str = br.readLine();
        for (int i = 0; i < str.length(); i++) {
            int dir = 0;
            if (str.charAt(i) == 'W') {
                if (resultMap[y][x] >= 2) {
                    continue;
                }
                bfs(y, x);
                continue;
            }
            switch (str.charAt(i)) {
                case 'U':
                    dir = 0;
                    break;
                case 'D':
                    dir = 1;
                    break;
                case 'L':
                    dir = 2;
                    break;
                case 'R':
                    dir = 3;
                    break;
            }
            y = y + dy[dir];
            x = x + dx[dir];
        }

        resultMap[y][x] = 2;
        for (int d = 0; d < 4; d++) {
            int ny = dy[d] + y;
            int nx = dx[d] + x;

            if (ny < 0 || nx < 0 || ny >= R || nx >= C || resultMap[ny][nx] == 2) {
                continue;
            }
            resultMap[ny][nx] = 2;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(resultMap[i][j] == 0 ? '#' : '.');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}