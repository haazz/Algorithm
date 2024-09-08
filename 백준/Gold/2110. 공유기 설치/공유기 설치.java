import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());

        long[] homes = new long[(int) N];
        for (int i = 0; i < N; i++) {
            homes[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(homes);
        long start = 0;
        long end = homes[(int)N - 1];
        long result = 0;

        while (start <= end) {
            long mid = (start + end) / 2;
            long cnt = 1;
            long pos = homes[0];
            
            

            for (int i = 1; i < N; i++) {
                if (homes[i] - pos >= mid) {
                    cnt++;
                    pos = homes[i];
                }
            }

            if (cnt >= C) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(end);
    }
}