import java.io.*;
import java.util.*;

public class Main {
    static int T;
    static int K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            K = Integer.parseInt(br.readLine());
            PriorityQueue<Integer> minq = new PriorityQueue<>();
            PriorityQueue<Integer> maxq = new PriorityQueue<>(Collections.reverseOrder());
            Map<Integer, Integer> numCnt = new HashMap<>();

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                char op = st.nextToken().charAt(0);
                int num = Integer.parseInt(st.nextToken());

                if (op == 'I') {
                    minq.add(num);
                    maxq.add(num);
                    Integer cnt = numCnt.get(num);
                    if (cnt == null) {
                        numCnt.put(num, 1);
                    } else {
                        numCnt.replace(num, cnt + 1);
                    }
                } else {
                    if (maxq.isEmpty() || minq.isEmpty()) {
                        continue;
                    }
                    if (num == 1) {
                        int maxValue = maxq.poll();
                        Integer cnt = numCnt.get(maxValue);
                        while (!maxq.isEmpty() && (cnt == null || cnt <= 0)) {
                            maxValue = maxq.poll();
                            cnt = numCnt.get(maxValue);
                        }
                        if (cnt != null && cnt > 0) {
                            numCnt.replace(maxValue, cnt - 1);
                        }
                    } else {
                        int minValue = minq.poll();
                        Integer cnt = numCnt.get(minValue);
                        while (!minq.isEmpty() && (cnt == null || cnt <= 0)) {
                            minValue = minq.poll();
                            cnt = numCnt.get(minValue);
                        }
                        if (cnt != null && cnt > 0) {
                            numCnt.replace(minValue, cnt - 1);
                        }
                    }
                }

            }

            if (minq.isEmpty() || maxq.isEmpty()) {
                sb.append("EMPTY");
            } else {
                int minValue = minq.poll();
                Integer minCnt = numCnt.get(minValue);

                while (!minq.isEmpty() && (minCnt == null || minCnt <= 0)) {
                    minValue = minq.poll();
                    minCnt = numCnt.get(minValue);
                }
                int maxValue = maxq.poll();
                Integer maxCnt = numCnt.get(maxValue);
                while (!maxq.isEmpty() && (maxCnt == null || maxCnt <= 0)) {
                    maxValue = maxq.poll();
                    maxCnt = numCnt.get(maxValue);
                }

                if (minCnt == null || minCnt <= 0) {
                    sb.append("EMPTY");
                } else {
                    sb.append(maxValue);
                    sb.append(" ");
                    sb.append(minValue);
                }
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}