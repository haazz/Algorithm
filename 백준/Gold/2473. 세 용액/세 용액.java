import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int N;
    static final long MAX_VALUE = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        long[] liquid = new long[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            liquid[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(liquid);

        long[] result = new long[3];
        result[0] = MAX_VALUE;
        result[1] = MAX_VALUE;
        result[2] = MAX_VALUE;

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                int start = j + 1;
                int end = N - 1;

                while (start <= end) {
                    int mid = (start + end) / 2;

                    if (Math.abs(liquid[i] + liquid[j] + liquid[mid]) < Math
                            .abs(result[0] + result[1] + result[2])) {
                        result[0] = liquid[i];
                        result[1] = liquid[j];
                        result[2] = liquid[mid];
                    }
                    
                    if (liquid[i] + liquid[j] + liquid[mid] > 0) {
                        end = mid - 1;
                    } else {
                        start = mid + 1;
                    }
                }
            }
        }

        Arrays.sort(result);

        System.out.println(result[0] + " " + result[1] + " " + result[2]);

    }
}