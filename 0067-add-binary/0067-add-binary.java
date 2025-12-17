class Solution {
    public String addBinary(String a, String b) {
        int ml = Math.max(a.length(), b.length());
        int tmp = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= ml; i++) {    
            int ai = a.length() - i;
            int bi = b.length() - i;

            if (ai >= 0 && a.charAt(ai) == '1') {
                tmp++;
            }
            if (bi >= 0 && b.charAt(bi) == '1') {
                tmp++;
            }

            if (tmp == 0) {
                sb.append('0');
                tmp = 0;
            } else if (tmp == 1) {
                sb.append('1');
                tmp = 0;
            } else if (tmp == 2) {
                sb.append('0');
                tmp = 1;
            } else {
                sb.append('1');
                tmp = 1;
            }
        }
        if (tmp >= 1) {
            sb.append('1');
        }

        return sb.reverse().toString();
    }
}