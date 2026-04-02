class Solution {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        Queue<Integer> q = new LinkedList<>();
        
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
                q.add(ri);
                if (hm.get(ch) >= 0 && ++cnt >= t.length() && mLen > ri - li) {
                    mLen = ri - li;
                    mli = li;
                    mri = ri;
                }
                
                // minus proccess
                while (hm.get(s.charAt(q.peek())) < 0) {
                    char lch = s.charAt(q.poll());
                    hm.put(lch, hm.get(lch) + 1);
                    li = q.peek();
                    if (cnt >= t.length() && mLen > ri - li) {
                        mLen = ri - li;
                        mli = li;
                        mri = ri;
                    }

                }
            }
            if (q.isEmpty()) {
                li++;
            }
        }
        if (cnt < t.length()) {
            return  "";
        }
        return s.substring(mli, mri + 1);
    }
}