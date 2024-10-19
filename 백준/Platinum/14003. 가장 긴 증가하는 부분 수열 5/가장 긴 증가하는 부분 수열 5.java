import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int size;
    static long[] nums;
    static StringBuilder sb = new StringBuilder();

    public static int binarySearch(int[] dp, int start, int end, long key) {
        int result = 0;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (dp[mid] == -1 || nums[dp[mid]] >= key) {
                result = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        nums = new long[N];
        int[] dp = new int[N];
        int[] reverseTrace = new int[N];
        size = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Long.parseLong(st.nextToken());
            dp[i] = -1;
            reverseTrace[i] = -1;
        }

        for (int i = 0; i < N; i++) {
            int currentIdx = binarySearch(dp, 0, size, nums[i]);
            if (currentIdx >= size) {

                size++;
            }
            dp[currentIdx] = i;
            if (currentIdx > 0) {
                reverseTrace[i] = dp[currentIdx - 1];
            }
        }

        List<Long> numList = new ArrayList<>();

        int i = dp[size - 1];
        while (reverseTrace[i] > -1) {
            numList.add(nums[i]);
            i = reverseTrace[i];
        }
        numList.add(nums[i]);

        for (int j = numList.size() - 1; j >= 0; j--) {
            sb.append(numList.get(j));
            sb.append(" ");
        }

        System.out.println(size);
        System.out.println(sb);
    }
}