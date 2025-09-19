class Solution {
    public int longestPalindrome(String s) {
        int answer = 0;
        Set<Character> st = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (st.contains(ch)) {
                st.remove(ch);
                answer += 2;
            } else {
                st.add(ch);
            }
        }

        if (!st.isEmpty()) {
            answer++;
        }
        return answer;
    }
}