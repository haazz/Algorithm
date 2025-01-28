import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    static int N;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    static int[] result;

    public static void permut(int depth) {
        if (depth >= N) {
            for (int i = 0; i < N; i++) {
                sb.append(result[i] + " ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            result[depth] = i + 1;
            permut(depth + 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        result = new int[N];
        visited = new boolean[N];
        permut(0);
        System.out.print(sb);
    }
}