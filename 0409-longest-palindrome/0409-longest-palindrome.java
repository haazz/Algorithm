class Solution {
    public int longestPalindrome(String s) {
        int[] alp = new int[52];

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                alp[ch - 'a']++;
            } else {
                alp[ch - 'A' + 26]++;
            }
            
        }

        boolean single = false;
        int answer = 0;
        for (int i = 0; i < 52; i++) {
            answer += (alp[i] / 2) * 2;
            if (alp[i] % 2 == 1) {
                single = true;
            }
        }

        if (single) {
            answer++;
        }
        return answer;
    }
}