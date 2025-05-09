import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int current = 0;
        int step = 0;

        for (int i = 0; i < M; i++) {
            step++;
            current += Integer.parseInt(br.readLine());
            if (current >= N) {
                break;
            }
            current += arr[current];
            if (current >= N) {
                break;
            }
        }

        System.out.println(step);
    }
}