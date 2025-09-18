class Solution {
    public boolean comp(int[] cor, int[] alp) {
        for (int i = 0; i < 26; i++) {
            if (cor[i] != alp[i]) {
                return false;
            }
        }
        return true;
    }
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> answer = new ArrayList<>();
        if (s.length() < p.length()) {
            return answer;
        }
        int[] alp = new int[26];
        int[] cor = new int[26];

        for (int i = 0; i < p.length(); i++) {
            alp[s.charAt(i) - 'a']++;
            cor[p.charAt(i) - 'a']++;
        }
        if (comp(cor, alp)) {
            answer.add(0);
        }
        for (int i = p.length(); i < s.length(); i++) {
            alp[s.charAt(i) - 'a']++;
            alp[s.charAt(i - p.length()) - 'a']--;
            if (comp(cor, alp)) {
                answer.add(i - p.length() + 1);
            }
        }
        return answer;
    }
}