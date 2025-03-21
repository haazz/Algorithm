class Solution {
    
    public int updown(char tch) {
        if (tch - 'A' > 13) {
            return 'Z' - tch + 1;
        } else {
            return tch - 'A';
        }
    }
    
    public int solution(String name) {
        int result = 0;
        int n = name.length();
        int move = n - 1;
        
        for (int i = 0; i < n; i++) {
            result += updown(name.charAt(i));
            
            int j = i + 1;
            while (j < n && name.charAt(j) == 'A') {
                j++;
            }
            move = Math.min(move, i * 2 + n - j);
            move = Math.min(move, (n - j) * 2 + i);
        }
        
        return result + move;
    }
}