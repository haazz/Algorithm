import java.util.*;
import java.io.*;

class Main {
    public static int[] uf;

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        uf[x] = y;
    }

    public static int find(int x) {
        if (x == uf[x]) {
            return x;
        }
        return uf[x] = find(uf[x]);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] squares = new int[N][4];
        uf = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            uf[i] = i;
        }

        boolean isStartAtZero = false;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                squares[i][j] = Integer.parseInt(st.nextToken());
            }
            if (!isStartAtZero) {
                isStartAtZero = isStartAtZ(squares[i]);
            }
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (!isDup(squares[i], squares[j])) {
                    continue;
                }
                union(i, j);
            }
        }

        Set<Integer> s = new HashSet<>();

        for (int i = 0; i < N; i++) {
            s.add(find(uf[i]));
        }
        System.out.println(isStartAtZero ? s.size() - 1 : s.size());
    }

    public static boolean isDup(int[] sq1, int[] sq2) {
        if ((sq1[0] > sq2[2]) || (sq1[2] < sq2[0]) || (sq1[1] > sq2[3]) || (sq1[3] < sq2[1]) ||
                ((sq1[0] < sq2[0]) && (sq1[2] > sq2[2]) && (sq1[1] < sq2[1]) && (sq1[3] > sq2[3])) ||
                ((sq2[0] < sq1[0]) && (sq2[2] > sq1[2]) && (sq2[1] < sq1[1]) && (sq2[3] > sq1[3]))) {
            return false;
        }
        return true;
    }

    public static boolean isStartAtZ(int[] sq) {
        if (((sq[0] == 0 || sq[2] == 0) && sq[1] <= 0 && sq[3] >= 0) ||
                ((sq[1] == 0 || sq[3] == 0) && sq[0] <= 0 && sq[2] >= 0)) {
            return true;
        }
        return false;
    }
}