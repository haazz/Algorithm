import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        long[][] vertex = new long[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            vertex[i][0] = Long.parseLong(st.nextToken());
            vertex[i][1] = Long.parseLong(st.nextToken());
        }

        long sum = 0;

        for (int i = 0; i < N - 1; i++) {
            sum += vertex[i][0] * vertex[i + 1][1];
            sum -= vertex[i][1] * vertex[i + 1][0];
        }
        sum += vertex[N - 1][0] * vertex[0][1];
        sum -= vertex[N - 1][1] * vertex[0][0];

        System.out.printf("%.1f", Math.abs(sum) / 2.0);
    }
}