/*
스택 문제
*/

class Solution {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '(':
                case '{':
                case '[':
                    st.push(s.charAt(i));
                    break;                
                case ')': 
                    if (st.isEmpty() || st.pop() != '(') {
                        return false;
                    }
                    break;
                case '}': 
                    if (st.isEmpty() || st.pop() != '{') {
                        return false;
                    }
                    break;
                case ']':
                    if (st.isEmpty() || st.pop() != '[') {
                        return false;
                    }
                    break;
            }
        }
        if (st.isEmpty()) {
            return true;
        }
        return false;
    }
}