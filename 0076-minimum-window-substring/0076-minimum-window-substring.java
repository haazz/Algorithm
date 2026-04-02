class Solution {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        HashMap<Character, Integer> win = new HashMap<Character, Integer>();
        
        for (int i  = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            hm.put(ch, hm.getOrDefault(ch, 0) + 1);
        }
        
        int target = hm.size();
        int cnt = 0;
        int li = 0;
        int rli = 0;
        int rri = Integer.MAX_VALUE;

        for (int ri = 0; ri < s.length(); ri++) {
            char ch = s.charAt(ri);

            win.put(ch, win.getOrDefault(ch, 0) + 1);

            if (hm.containsKey(ch) && hm.get(ch).equals(win.get(ch))) {
                cnt++;

            }

            while (cnt == target) {
                
                if (rri - rli > ri - li) {
                    rli = li;
                    rri = ri;
                }

                char lch = s.charAt(li);
                
                win.put(lch, win.get(lch) - 1);

                if (hm.containsKey(lch) && hm.get(lch) > win.get(lch)) {    
                    cnt--;
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