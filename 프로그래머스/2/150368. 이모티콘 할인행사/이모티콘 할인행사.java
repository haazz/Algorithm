class Solution {
    int[] answer;
    int[] sale = new int[] {40, 30, 20, 10};
    
    public void comb(int[][] users, int[] emoticons, int[] emoSale, int start, int depth) {
        if (depth >= emoticons.length) {
            int[] val = new int[2];
            int[] pay = new int[users.length];
            
            for (int i = 0; i < users.length; i++) {
                for (int j = 0; j < emoSale.length; j++) {
                    if (users[i][0] <= emoSale[j]) {
                        if (pay[i] >= users[i][1]) {
                            continue;
                        }
                        pay[i] += emoticons[j];
                        val[1] += emoticons[j];
                        if (pay[i] >= users[i][1]) {
                            val[0]++;
                            val[1] -= pay[i];
                        }
                    }
                }
            }
            
            if (val[0] == answer[0]) {
                answer[1] = Math.max(val[1], answer[1]);
            } else if (val[0] > answer[0]) {
                answer[0] = val[0];
                answer[1] = val[1];
            }
            return;
        }
        
        for (int i = start; i < emoticons.length; i++) {
            for (int j = 0; j < 4; j++) {
                int og = emoticons[i];
                emoticons[i] = emoticons[i] * (100 - sale[j]) / 100;
                emoSale[i] = sale[j];
                comb(users, emoticons, emoSale, i + 1, depth + 1);
                emoticons[i] = og;
            }
        }
    }
    
    public int[] solution(int[][] users, int[] emoticons) {
        answer = new int[2];
        comb(users, emoticons, new int[emoticons.length], 0, 0);
        
        return answer;
    }
}