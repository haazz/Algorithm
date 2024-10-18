import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.HashMap;

public class Main {
    static int N;
    static int idx;

    public static int binarySearch(long[] array, int from, int to, long key) {
        int result = 0;

        while (from <= to) {
            int mid = (from + to) / 2;

            // if (key == array[mid]) {
            // return -1;
            // } else
            if (key <= array[mid]) {
                to = mid - 1;
                result = mid;
            } else {
                from = mid + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        long[] sequence = new long[N];
        long[] dp = new long[N];
        idx = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sequence[i] = Long.parseLong(st.nextToken());
            dp[i] = Long.MAX_VALUE;
        }

        for (int i = 0; i < N; i++) {
            int currentIdx = binarySearch(dp, 0, idx + 1, sequence[i]);

            if (currentIdx > idx) {
                idx++;
            }
            dp[currentIdx] = sequence[i];
        }

        System.out.println(idx + 1);
    }
}