/*
deque에다가 문자를 넣자
앞뒤로 하나씩 빼기
 */

class Solution {
    public boolean isPalindrome(String s) {
        Deque<Character> dq = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c >= 'A' && c <= 'Z') {
                c = (char)(c - 'A' + 'a');
                dq.add(c);
            } else if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
                dq.add(c);
            }
        }

        while (!dq.isEmpty()) {
            char f = dq.pollLast();

            if (!dq.isEmpty() && f != dq.poll()) {
                return false;
            }
        }
        return true;
    }

    public boolean isAlphaNumeric(char c) {
        if ((c >= 'a' && c <= 'Z') || (c >= '0' && c <= '9')) {
            return true;
        }
        return false;
    }
}