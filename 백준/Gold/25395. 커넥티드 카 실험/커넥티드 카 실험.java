import java.util.*;
import java.io.*;

public class Main {
    static int N, S;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        int[][] cars = new int[N][3];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cars[i][0] = i;
            cars[i][1] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cars[i][2] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cars, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        for (int i = 0; i < N; i++) {
            if (cars[i][0] == S - 1) {
                S = i;
                break;
            }
        }
        List<Integer> result = new ArrayList<>();
        int minRange = cars[S][1] - cars[S][2];
        int maxRange = cars[S][1] + cars[S][2];
        int minPos = S - 1;
        int maxPos = S + 1;
        boolean isVisit = true;
        result.add(cars[S][0] + 1);

        while (isVisit) {
            isVisit = false;
            if (minPos >= 0 && minRange <= cars[minPos][1]) {
                minRange = Math.min(minRange, cars[minPos][1] - cars[minPos][2]);
                maxRange = Math.max(maxRange, cars[minPos][1] + cars[minPos][2]);
                result.add(cars[minPos][0] + 1);
                minPos--;
                isVisit = true;
            }

            if (maxPos < N && maxRange >= cars[maxPos][1]) {
                minRange = Math.min(minRange, cars[maxPos][1] - cars[maxPos][2]);
                maxRange = Math.max(maxRange, cars[maxPos][1] + cars[maxPos][2]);
                result.add(cars[maxPos][0] + 1);
                maxPos++;
                isVisit = true;
            }

        }

        // for (int i = 0; i < N; i++) {
        // for (int j = 0; j < 3; j++) {
        // System.out.print(cars[i][j] + " ");
        // }
        // System.out.println();
        // }
        Collections.sort(result);

        for (int num : result) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
}