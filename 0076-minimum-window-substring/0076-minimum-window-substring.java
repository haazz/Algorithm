class Solution {
    public String minWindow(String s, String t) {
        int[] alp = new int[52];

        for (int i = 0; i < t.length(); i++) {
            int val;
            if (t.charAt(i) >= 'a' && t.charAt(i) <= 'z') {
                val = t.charAt(i) - 'a';
                alp[val]++;
            } else {
                val = t.charAt(i) - 'A' + 26;
                alp[val]++;
            }
        }

        int cnt = t.length();
        int msi = 0;
        int start = 0;
        int minSize = Integer.MAX_VALUE;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int val = ch >= 'a' && ch <= 'z' ? ch - 'a' : ch - 'A' + 26;

            if (--alp[val] >= 0) {
                cnt--;
            }

            while (cnt <= 0) {
                int sVal = s.charAt(start) >= 'a' && s.charAt(start) <= 'z' ? s.charAt(start) - 'a' : s.charAt(start) - 'A' + 26;
                if (++alp[sVal] > 0) {
                    cnt++;
                    if (minSize > i - start) {
                        msi = start;
                        minSize = i - start;
                    }
                }
                start++;
            }
        }
        return minSize == Integer.MAX_VALUE ? "" : s.substring(msi, msi + minSize + 1);
    }
}