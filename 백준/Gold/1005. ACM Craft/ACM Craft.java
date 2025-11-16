
// 다이나믹 프로그래밍으로 풀기
// 연속 밟기에 조심해서 풀기 2차원 [3][N]으로 풀 수 있지 않을까?
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int K;
    static int[] bu;
    static int[] preBu;
    static int[] time;
    static List<List<Integer>> graph;
    static int target;

    public static int sol() {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (preBu[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int elem = q.poll();

            for (int next : graph.get(elem)) {
                preBu[next]--;
                time[next] = Math.max(time[next], time[elem] + bu[elem]);
                if (preBu[next] <= 0) {
                    q.add(next);
                }
            }
        }

        return time[target] + bu[target];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            bu = new int[N + 1];
            preBu = new int[N + 1];
            time = new int[N + 1];
            graph = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            graph.add(new ArrayList<>());
            for (int i = 1; i <= N; i++) {
                bu[i] = Integer.parseInt(st.nextToken());
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                preBu[y]++;
                graph.get(x).add(y);
            }
            target = Integer.parseInt(br.readLine());

            sb.append(sol()).append('\n');
        }
        System.out.println(sb.toString());
    }
}