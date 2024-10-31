import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.*;

class Main {
    static int N;
    static int M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Map<String, String> pokeString = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String poke = br.readLine();
            pokeString.put(poke, (i + 1) + "");
            pokeString.put((i + 1) + "", poke);
        }

        for (int i = 0; i < M; i++) {
            sb.append(pokeString.get(br.readLine()));
            sb.append("\n");
        }

        System.out.println(sb);

    }
}