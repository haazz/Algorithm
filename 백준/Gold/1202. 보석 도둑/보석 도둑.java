import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int K;
    static int[] bags;
    static boolean[] visitBags;

    public static boolean findBag(int[] jewel) {
        int start = 0;
        int end = K - 1;
        int result = -1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (bags[mid] >= jewel[0]) {
                end = mid - 1;
                if (!visitBags[mid]) {
                    result = mid;
                }
            } else {
                start = mid + 1;
            }
        }

        if (result == -1) {
            return false;
        }   
        visitBags[result] = true;
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        bags = new int[K];
        visitBags = new boolean[K];
        long result = 0;

        PriorityQueue<int[]> jewels = new PriorityQueue<int[]>((a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            jewels.add(new int[] { Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()) });
        }

        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bags);

        for (int i = 0; i < K; i++) {
            while (!jewels.isEmpty() && bags[i] >= jewels.peek()[0]) {
                pq.add(jewels.poll()[1]);
            }
            if (!pq.isEmpty()) {
                result += pq.poll();
            }
        }

        System.out.println(result);
    }
}