import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] nums = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        nums[0] = 0;
        for (int i = 1; i < N + 1; i++) {
            nums[i] = nums[i - 1] + Integer.parseInt(st.nextToken());
        }

        int len = Integer.MAX_VALUE;
        for (int i = 0; i < N + 1; i++) {
            int start = i;
            int end = N;
            int result = Integer.MAX_VALUE;

            while (start <= end) {
                int mid = (start + end) / 2;

                if (nums[mid] - nums[i] >= S) {
                    result = mid - i;
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            len = Math.min(len, result);
        }

        if (len == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(len);
        }

    }
}