import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.HashMap;

public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int[] lamen = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            lamen[i] = Integer.parseInt(st.nextToken());
        }

        long price = 0;
        int i;
        for (i = 0; i < N - 2; i++) {
            int l2 = 0;
            int l3 = 0;

            if (lamen[i + 1] != 0) {
                l2 = lamen[i + 1] > lamen[i] ? lamen[i] : lamen[i + 1];
                if (lamen[i + 2] >= lamen[i + 1]) {
                    l3 = l2;
                } else {
                    int diff = l2 - (lamen[i + 1] - lamen[i + 2]);

                    if (diff > 0) {
                        l3 = lamen[i + 2] - diff <= 0 ? lamen[i + 2] : diff;
                    }
                }
            }

            price += lamen[i] * 3 + l2 * 2 + l3 * 2;
            lamen[i] = 0;
            lamen[i + 1] -= l2;
            lamen[i + 2] -= l3;
        }

        if (lamen[i] >= lamen[i + 1]) {
            price += (lamen[i] * 3) + (lamen[i + 1] * 2);
        } else {
            price += (lamen[i] * 3) + (lamen[i] * 2) + ((lamen[i + 1] - lamen[i]) * 3);
        }

        System.out.println(price);
    }
}