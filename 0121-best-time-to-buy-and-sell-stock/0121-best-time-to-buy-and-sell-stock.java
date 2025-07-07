/*

*/

class Solution {
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        int profit = 0;

        for (int i = 0; i < prices.length; i++) {
            if (min > prices[i]) {
                min = prices[i];
                max = prices[i];
            } else {
                max = Math.max(prices[i], max);
            }
            profit = Math.max(max - min, profit);
        }
        return profit;
    }
}