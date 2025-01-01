import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        String s = br.readLine();
        int stringSize = s.length();
        char[] result = new char[stringSize];

        for (int i = 0; i < stringSize; i++) {
            result[i] = s.charAt(i);
        }
        for (int i = 1; i < N; i++) {
            s = br.readLine();
            for (int j = 0; j < stringSize; j++) {
                if (result[j] != s.charAt(j)) {
                    result[j] = '?';
                }
            }
        }

        for (int i = 0; i < stringSize; i++) {
            System.out.print(result[i]);
        }
    }
}
