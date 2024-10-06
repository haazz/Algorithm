import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int L;
    static int K;
    static int C;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        long[] cuts = new long[K + 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            cuts[i] = Long.parseLong(st.nextToken());
        }
        cuts[K] = L;
        cuts[K + 1] = 0;

        Arrays.sort(cuts);
        long start = 0;
        long end = L;
        long maxLength = 0;
        long firstCut = 0;

        while (start <= end) {
            long mid = (start + end) / 2;

            long lastCut = L;
            long cnt = 0;
            for (int i = K; i >= 0; i--) {
                if (lastCut - cuts[i] > mid) {
                    if (cuts[i + 1] - cuts[i] > mid) {
                        cnt = C + 1; 
                        break;
                    }
                    lastCut = cuts[i + 1];
                    cnt++;
                }
            }

            if (cnt < C) {
                lastCut = cuts[1];
            }

            if (cnt > C) {
                start = mid + 1;
            } else {
                maxLength = mid;
                firstCut = lastCut;                   
                end = mid - 1;
            }
        } 

        System.out.println(maxLength + " " + firstCut);
    }
}
