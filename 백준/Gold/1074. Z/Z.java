import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int R;
    static int C;
    static int cnt = 0;
    static int result = 0;
    static final int[] dy = { 0, 0, 1, 1 };
    static final int[] dx = { 0, 1, 0, 1 };

    public static void div(int y, int x, int size) {
        if (size == 1) {
            return;
        }

        size = size / 2;

        if (y < size && x < size) {
            div(y, x, size);
        } else {
            if (y >= size) {
                cnt += size * size * 2;
                y -= size;
            }
            if (x >= size) {
                cnt += size * size;

                x -= size;
            }
            div(y, x, size);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        N = (int) Math.pow(2, N);
        div(R, C, N);

        System.out.println(cnt);

    }
}
