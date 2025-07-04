/**
1. 사전 정렬
2. 바이너리 서치?

아니면
1. 그 문자열 트리에 넣는거
2. 

20000 * 300
6,000,000

우선 단순하게 비교해서 구현해보자

=============================================

문제를 잘 못 이해했다
-> 사전에 있는 모든 단어로 완벽히 분리되는지를 확인하는 문제이다.

1. 단순한 구현으로는 시간 초과가 나온다
2. dp?

 */
class Solution {

    public boolean isEqual(String s, String word, int idx) {
        for (int i = 0; i < word.length(); i++) {
            if  (s.length() <= idx + i || word.charAt(i) != s.charAt(idx + i)) {
                return false;
            }
        }
        return true;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] can = new boolean[s.length() + 1];
        can[0] = true;

        for (int i = 0; i < s.length(); i++) {
            if (!can[i]) {
                continue;
            }
            for (String word : wordDict) {
                if (isEqual(s, word, i)) {
                    can[i + word.length()] = true;
                }
            }
        }
        return can[can.length - 1];
    }
}