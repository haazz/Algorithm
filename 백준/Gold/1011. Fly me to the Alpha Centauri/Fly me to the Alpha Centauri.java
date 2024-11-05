import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int N;

    public static int go(int x, int y) {
        int valx = 0;
        int valy = 0;
        int timex = 0;
        int timey = 0;
        int half = x + ((y - x) / 2);

        while (x < half) {
            valx++;
            x += valx;
            timex++;
        }
        while (x < y) {
            valy++;
            y -= valy;
            timey++;
        }

        // System.out.println(x + " " + y);
        return timex + timey;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            sb.append(
                    go(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            sb.append("\n");
        }
        System.out.print(sb);
    }
}