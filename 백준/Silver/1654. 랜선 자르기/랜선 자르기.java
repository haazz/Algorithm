import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        int[] lines = new int[K];

        for (int i = 0; i < K; i++) {
            lines[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(lines);
        long start = 1;
        long end = (long) Math.pow(2, 31);
        while (start < end) {
            long mid = (start + end) / 2;
            long cnt = 0;

            for (int i = 0; i < K; i++) {
                cnt += lines[i] / mid;
            }

            if (cnt < N) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(start - 1);
    }
}