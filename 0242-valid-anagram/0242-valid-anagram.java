class Solution {
    public boolean isAnagram(String s, String t) {
        int[] abc = new int[26];
        
        for (int i = 0; i < s.length(); i++) {
            abc[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            if (abc[t.charAt(i) - 'a']-- <= 0) {
                return false;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (abc[i] != 0) {
                return false;
            }
        }
        return true;
    }
}