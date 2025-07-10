/*
deque에다가 문자를 넣자
앞뒤로 하나씩 빼기
 */

class Solution {
    public boolean isPalindrome(String s) {
        List<Character> dq = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c >= 'A' && c <= 'Z') {
                c = (char)(c - 'A' + 'a');
                dq.add(c);
            } else if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
                dq.add(c);
            }
        }

        for (int i = 0; i < dq.size() / 2; i++) {
            if (dq.get(i) != dq.get(dq.size() - i - 1)) {
                return false;
            }
        }
        return true;
    }

}