class Solution {
    List<String> answer = new ArrayList<>();
    char[][] keys = {
            {'a', 'b', 'c'}, {'d', 'e', 'f'},
            {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w','x','y','z'}
        };
    
    public void comb(String digits, int start, StringBuilder st) {
        if (digits.length() <= start) {
            answer.add(st.toString());
            return;
        }

        for (char ch : keys[digits.charAt(start) - '2']) {
            st.append(ch);
            comb(digits, start + 1, st);
            st.deleteCharAt(st.length() - 1);
        }
    }
    public List<String> letterCombinations(String digits) {
        if (digits.length() <= 0) {
            return answer;
        }
        comb(digits, 0, new StringBuilder());
        return answer;
    }
}