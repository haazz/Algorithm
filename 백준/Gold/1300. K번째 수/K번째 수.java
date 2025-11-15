
// pq이용해서 무조건 큰 것부터 bs로 최적의 자르기 실행 k번 하면 끝?
import java.io.*;
import java.util.*;

public class Main {

    public static int bs(int N, int K) {
        int l = 0;
        int r = K;
        int answer = 0;

        while (l <= r) {
            int mid = (l + r) / 2;
            int cnt = 0;

            for (int i = 1; i <= N; i++) {
                cnt += Math.min(mid / i, N);
            }
            if (cnt >= K) {
                answer = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        System.out.println(bs(N, K));
    }
}