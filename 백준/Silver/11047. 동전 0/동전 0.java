import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int tokenCount = Integer.parseInt(st.nextToken());
        int goal = Integer.parseInt(st.nextToken());

        List<Integer> tokenList = new ArrayList<Integer>();
        for (int i = 0; i < tokenCount; i++) {
            tokenList.add(Integer.parseInt(br.readLine()));
        }

        int count = 0;
        for (int i = tokenCount - 1; i >= 0; i--) {
            if (goal >= tokenList.get(i)) {
                count += goal / tokenList.get(i);
                goal = goal % tokenList.get(i);
            }
        }
        System.out.println(count);
    }

}