class Solution {
    public int myAtoi(String s) {
        s = s.trim();
        int i = 0;
        long answer = 0;
        int sign = 1;

        if (s.length() == 0) {
            return 0;
        }

        if (s.charAt(i) == '-') {
            sign = -1;
            i++;
        } else if (s.charAt(i) == '+') {
            i++;
        }

        for ( ; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                break;
            }
            answer *= 10;
            answer += s.charAt(i) - '0';
            // System.out.println(answer);
            if (answer * sign >= Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            } else if (answer * sign <= Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }
        return (int) answer * sign;
    }
    
}