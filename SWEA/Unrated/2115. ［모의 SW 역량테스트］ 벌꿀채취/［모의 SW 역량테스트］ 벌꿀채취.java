import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int M;
    static int C;
    static int maxHoney;

    /**
     * Honey
     */
    public static class Honey implements Comparable {
        int i;
        int j;
        int value;

        public Honey(int i, int j, int value) {
            this.i = i;
            this.j = j;
            this.value = value;
        }

        @Override
        public int compareTo(Object o) {
            // TODO Auto-generated method stub
            return ((Honey) o).value - this.value;
        }
    }

    public static void dfs(int[][] board, int ci, int cj, int depth, int honey, int sum) {
        if (sum > C) {
            return;
        }

        if (depth >= M) {

            maxHoney = Math.max(maxHoney, honey);
            return;
        }
        dfs(board, ci, cj + 1, depth + 1, honey + (board[ci][cj] * board[ci][cj]), sum + board[ci][cj]);
        dfs(board, ci, cj + 1, depth + 1, honey, sum);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            PriorityQueue<Honey> pq = new PriorityQueue<>();
            int[][] board = new int[N][N];
            int result = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 꿀을 체집할 수 있는 모든 경우의 수의 최대 값을 우선순위 큐에 넣습니다.
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (j > N - M) {
                        continue;
                    }
                    maxHoney = 0;
                    dfs(board, i, j, 0, 0, 0);
                    pq.add(new Honey(i, j, maxHoney));
                }
            }

            // 최대 값을 board에 갱신해줍니다.
            Honey honey = pq.poll();
            result = honey.value;
            for (int j = 0; j < M; j++) {
                board[honey.i][honey.j + j] = -1;
            }
            // 최대값 위치를 제외한 다음으로 큰 값 위치를 체크합니다.
            while (!pq.isEmpty()) {
                honey = pq.poll();
                boolean check = false;
                for (int j = 0; j < M; j++) {
                    if (board[honey.i][honey.j + j] == -1) {
                        check = true;
                    }
                }
                if (check) {
                    continue;
                }
                result += honey.value;
                break;
            }
            System.out.println("#" + tc + " " + result);
        }
    }
}
