
// pq이용해서 무조건 큰 것부터 bs로 최적의 자르기 실행 k번 하면 끝?
import java.io.*;
import java.util.*;

public class Main {
    static int L;
    static int K;
    static int C;

    public static int bs(int[] pos, int lp, int rp, int l, int r) {
        int answer = -1;
        int aVal = Integer.MAX_VALUE;

        while (l <= r) {
            int mid = (l + r) / 2;
            int lv = pos[mid] - lp;
            int rv = rp - pos[mid];

            if (lv < rv) {
                l = mid + 1;
                if (aVal >= rv) {
                    aVal = rv;
                    answer = mid;
                }
            } else {
                r = mid - 1;
                if (aVal > lv) {
                    aVal = lv;
                    answer = mid;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] answer = new long[2];

        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        List<Integer> pos = new ArrayList<>();
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < K; i++) {
            pos.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(pos);
        pos.add(0, 0);
        pos.add(L);

        int l = 0;
        int r = L;

        while (l <= r) {
            int mid = (l + r) / 2;
            int cnt = 0;
            int lPos = L;

            for (int i = K; i >= 0; i--) {
                if (lPos - pos.get(i) > mid) {
                    if (pos.get(i + 1) - pos.get(i) > mid) {
                        cnt = C + 1;
                        break;
                    }
                    cnt++;
                    lPos = pos.get(i + 1);
                }
            }

            if (cnt > C) {
                l = mid + 1;
            } else {
                r = mid - 1;
                answer[0] = mid;
                answer[1] = cnt < C ? pos.get(1) : lPos;
            }
        }

        System.out.println(answer[0] + " " + answer[1]);
    }
}