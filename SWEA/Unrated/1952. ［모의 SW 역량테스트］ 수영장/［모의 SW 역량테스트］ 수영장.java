import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 완전탐색 dfs
 */
class Solution {
    static int minPrice;
    static final int MONTH = 12;

    public static void dfs(int[] plan, int[] prices, int idx, int totalPrice) {
        if (idx >= 12) {
            if (totalPrice < minPrice) {
                minPrice = totalPrice;
            }
            return;
        }
        
        if (plan[idx] == 0) {
            dfs(plan, prices, idx + 1, totalPrice);
            return;
        }

        int[] tmp = new int[3];
        for (int i = 0; i < 3; i++) {
            if (idx + i >= MONTH) {
                break;
            }
            tmp[i] = plan[idx + i];
            plan[idx + i] = 0;
            if (i == 0) {
                if (tmp[i] * prices[0] > prices[1]) {
                    dfs(plan, prices, idx + 1, totalPrice + prices[1]);
                } else {
                    dfs(plan, prices, idx + 1, totalPrice + (tmp[i] * prices[0]));
                }
            }
        }
        dfs(plan, prices, idx + 3, totalPrice + prices[2]);
        
        // 값 복구
        for (int i = 0; i < 3; i++) {
            if (idx + i >= MONTH) {
                break;
            }
            plan[idx + i] = tmp[i];
        }
    }
    public static void main(String[] args) throws IOException{
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        
        for (int test_case = 1; test_case <= T; test_case++) {
            int[] prices = new int[4];
            int[] plan = new int[MONTH];
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                prices[i] = Integer.parseInt(st.nextToken());
            }
            minPrice = prices[3];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < MONTH; i++) {
                plan[i] = Integer.parseInt(st.nextToken());
            }
            dfs(plan, prices, 0, 0);
            System.out.printf("#%d %d\n", test_case, minPrice);
        }
    }
}