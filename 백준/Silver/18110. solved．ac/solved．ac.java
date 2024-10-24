import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        long remove = Math.round(N * 15 / (double) 100);
        for (int i = 0; i < remove; i++) {
            pq.poll();
        }

        int sumScore = 0;
        long nN = N - (remove * 2);
        for (int i = 0; i < nN; i++) {
            sumScore += pq.poll();
        }

        System.out.println(Math.round(sumScore / (double) nN));
    }
}