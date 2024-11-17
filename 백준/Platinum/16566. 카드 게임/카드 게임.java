import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int K;
    static int[] uf;
    static int[] blues;

    public static void union(int num1, int num2) {
        num1 = find(num1);
        num2 = find(num2);
        if (num1 > num2) {
            uf[num2] = num1;
        } else {
            uf[num1] = num2;
        }
    }

    public static int find(int num1) {
        if (num1 == uf[num1]) {
            return num1;
        }
        return uf[num1] = find(uf[num1]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        uf = new int[M];
        blues = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            uf[i] = i;
            blues[i] = num;
        }

        Arrays.sort(blues);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int red = Integer.parseInt(st.nextToken());
            int start = 0;
            int end = M - 1;
            int result = 0;

            while (start <= end) {
                int mid = (start + end) / 2;

                if (blues[mid] > red) {
                    end = mid - 1;
                    result = mid;
                } else {
                    start = mid + 1;
                }
            }

            int idx = find(result);
            sb.append(blues[idx]);
            sb.append("\n");
            if (idx < M - 1) {
                union(result, idx + 1);
            }
        }
        System.out.print(sb);
    }
}