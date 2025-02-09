import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> {
            if (a[1] == b[1]) {
                if (a[2] == b[2]) {
                    return b[3] - a[3];
                }
                return b[2] - a[2];
            }
            return b[1] - a[1];
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) });
        }

        int[] preMedals = new int[4];
        preMedals[1] = -1;
        int rank = 0;
        int count = 1;
        while (!pq.isEmpty()) {
            int[] elem = pq.poll();

            if (elem[1] != preMedals[1] || elem[2] != preMedals[2] || elem[3] != preMedals[3]) {
                rank = count;
            }
            // System.out.println(rank + " " + count);
            if (elem[0] == K) {
                System.out.println(rank);
                break;
            }
            preMedals = elem;
            count++;
        }
    }
}