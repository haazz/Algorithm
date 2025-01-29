import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            sb.append("LoveisKoreaUniversity ");
        }
        System.out.println(sb);

    }
}