class Solution {
    public String longestPalindrome(String s) {
        String answer = "";
    
        for (int i = 0; i < s.length(); i++) {
            // even
            int l = i;
            int r = i + 1;
            
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                String sub = s.substring(l, r + 1);
                if (answer.length() < sub.length()) {
                    answer = sub;
                }
                l--;
                r++;
            }

            // odd
            l = i;
            r = i;
            
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                String sub = s.substring(l, r + 1);
                if (answer.length() < sub.length()) {
                    answer = sub;
                }
                l--;
                r++;
            }
        }
        return answer;
    }
}