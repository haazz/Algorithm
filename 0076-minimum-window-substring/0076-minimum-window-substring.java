class Solution {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        
        for (int i  = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            hm.put(ch, hm.getOrDefault(ch, 0) + 1);
        }
        
        int cnt = 0;
        int mLen = Integer.MAX_VALUE;
        int mli = 0;
        int mri = 0;
        int li = 0;

        for (int ri = 0; ri < s.length(); ri++) {
            char ch = s.charAt(ri);
            if (hm.containsKey(ch)) {
                // general proccess
                hm.put(ch, hm.get(ch) - 1);
                if (hm.get(ch) >= 0 && ++cnt >= t.length() && mLen > ri - li) {
                    mLen = ri - li;
                    mli = li;
                    mri = ri;
                }
                
                // minus proccess
                char lch = s.charAt(li);
                while (!hm.containsKey(lch) || hm.get(lch) < 0) {
                    if (hm.containsKey(lch)) {
                        hm.put(lch, hm.get(lch) + 1);
                    }
                    li++;
                    lch = s.charAt(li);
                }
                if (cnt >= t.length() && mLen > ri - li) {
                    mLen = ri - li;
                    mli = li;
                    mri = ri;
                }
                
            }
        }
        if (cnt < t.length()) {
            return  "";
        }
        return s.substring(mli, mri + 1);
    }
}