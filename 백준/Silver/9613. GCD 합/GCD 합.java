import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static List<Integer> nums;
    static int nSize;

    public static int gcd(int num1, int num2) {
        if (num2 % num1 == 0) {
            return num1;
        }
        return gcd(num2 % num1, num1);
    }

    public static long comb() {
        long res = 0;

        for (int i = 0; i < nSize - 1; i++) {
            for (int j = i + 1; j < nSize; j++) {
                res += gcd(nums.get(i), nums.get(j));
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < N; tc++) {
            nums = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            nSize = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                nums.add(Integer.parseInt(st.nextToken()));
            }
            Collections.sort(nums);
            sb.append(comb() + "\n");
        }
        System.out.print(sb);
    }
}
