import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;

    public static void func(List<Integer>[] students, int[] preTable) {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i < N + 1; i++) {
            if (preTable[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int node = q.poll();
            System.out.print(node + " ");

            for (int i = 0; i < students[node].size(); i++) {
                int num = students[node].get(i);
                preTable[num]--;
                if (preTable[num] == 0) {
                    q.add(num);
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        List<Integer>[] students = new List[N + 1];
        int[] preTable = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            students[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            students[num1].add(num2);
            preTable[num2]++;
        }

        func(students, preTable);

    }
}
