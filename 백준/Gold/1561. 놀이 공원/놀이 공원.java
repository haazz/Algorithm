import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static long N;
    static long M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());

        long[] rides = new long[(int) M];
        long end = 0;
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            rides[i] = Long.parseLong(st.nextToken());
            end = Math.max(end, rides[i]);
        }

        if (N < M) {
            System.out.println(N);
            return;
        }

        long start = 0;
        long result = 0;
        end *= N;
        while (start <= end) {
            
            long mid = (start + end) / 2;
            long cnt = M;

            for (int i = 0; i < M; i++) {
                cnt += mid / rides[i];
                if (cnt >= N) {
                    break;
                }
            }
            

            if (cnt >= N) {
                end = mid - 1;
                result = mid;
            } else {
                start = mid + 1;
            }
        }

        // System.out.println(result);
        long cnt = 0;
        if (result != 0) {
            cnt = M;
            for (int i = 0; i < M; i++) {
                cnt += (result - 1) / rides[i];
            }
        }

        for (int i = 0; i < M; i++) {
            if (result % rides[i] == 0) {
                cnt++;
            }
            // System.out.println(cnt);
            if (cnt == N) {
                System.out.println(i + 1);
                break;
            }
        }
    }
}