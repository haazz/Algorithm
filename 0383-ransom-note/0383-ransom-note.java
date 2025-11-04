class Solution {

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] rans = new int[26];

        for (int i = 0; i < ransomNote.length(); i++) {
            rans[ransomNote.charAt(i) - 'a']++;
        }

        for (int i = 0; i < magazine.length(); i++) {
            rans[magazine.charAt(i) - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (rans[i] > 0) {
                return false;
            }
        }
        return true;
    }
}