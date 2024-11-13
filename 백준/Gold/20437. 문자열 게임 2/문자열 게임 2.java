import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            String s = br.readLine();
            K = Integer.parseInt(br.readLine());
            Map<Character, List<Integer>> charCnt = new HashMap<>();
            int minSize = Integer.MAX_VALUE;
            int maxSize = Integer.MIN_VALUE;

            if (K == 1) {
                sb.append("1 1");
                sb.append("\n");
                continue;
            }

            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (charCnt.get(ch) == null) {
                    charCnt.put(ch, new ArrayList<>());
                    charCnt.get(ch).add(i);
                } else {
                    int charCntSize = charCnt.get(ch).size() + 1;
                    if (charCntSize >= K) {
                        int len = i - charCnt.get(ch).get(charCntSize - K) + 1;
                        minSize = Math.min(minSize, len);
                        maxSize = Math.max(maxSize, len);
                    }
                    charCnt.get(ch).add(i);
                }
            }

            if (minSize == Integer.MAX_VALUE || maxSize == Integer.MIN_VALUE) {
                sb.append(-1);
            } else {
                sb.append(minSize);
                sb.append(" ");
                sb.append(maxSize);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}