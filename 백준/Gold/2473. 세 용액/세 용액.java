import java.io.*;
import java.util.*;

public class Main {
    public static int bs(long[] req, long target, int l, int r) {
        int answer = r;
        long minsub = Long.MAX_VALUE;

        while (l <= r) {
            int mid = (l + r) / 2;
            long sub = Math.abs(req[mid] - target);
            if (minsub > sub) {
                minsub = sub;
                answer = mid;
            }
            if (req[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        long[] req = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            req[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(req);
        long[] answer = new long[3];
        long minsub = Long.MAX_VALUE;
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                int k = bs(req, (req[i] + req[j]) * -1, j + 1, N - 1);
                long sum = Math.abs(req[i] + req[j] + req[k]);
                if (minsub > sum) {
                    minsub = sum;
                    answer[0] = req[i];
                    answer[1] = req[j];
                    answer[2] = req[k];
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            System.out.print(answer[i] + " ");
        }
        System.out.println();
    }
}