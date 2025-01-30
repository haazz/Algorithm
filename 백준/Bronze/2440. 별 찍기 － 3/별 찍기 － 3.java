import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - i; j++) {
                sb.append("*");
            }
            sb.append("\n");
        }
        System.out.print(sb);

    }
}