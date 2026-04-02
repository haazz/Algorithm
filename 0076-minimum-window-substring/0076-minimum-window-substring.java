class Solution {
    public String minWindow(String s, String t) {
        int[] hm = new int[128];
        int[] win = new int[128];
        
        for (int i  = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            hm[ch]++;
        }
        
        int target = t.length();
        int cnt = 0;
        int li = 0;
        int rli = 0;
        int rri = Integer.MAX_VALUE;

        for (int ri = 0; ri < s.length(); ri++) {
            char ch = s.charAt(ri);

            win[ch]++;

            if (hm[ch] != 0 && hm[ch] == win[ch]) {
                cnt += win[ch];
            }

            while (cnt == target) {
                
                if (rri - rli > ri - li) {
                    rli = li;
                    rri = ri;
                }

                char lch = s.charAt(li);
                win[lch]--;

                if (hm[lch] != 0 && hm[lch] > win[lch]) {    
                    cnt -= (win[lch] + 1);
                }
                li++;
            }

        }


        if (rri == Integer.MAX_VALUE) {
            return "";
        }
        return s.substring(rli, rri + 1);
    }
}