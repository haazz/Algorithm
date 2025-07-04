class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        int maxLen = 0;
        Set<String> wordSet = new HashSet<>(wordDict);
        dp[0] = true;

        for (String word : wordDict) {
            maxLen = Math.max(word.length(), maxLen);
        }

        for (int end = 1; end <= s.length(); end++) {
            for (int start = end - 1; start >= Math.max(0, end - maxLen); start--) {
                if (dp[start] && wordSet.contains(s.substring(start, end))) {
                    dp[start + (end - start)] = true;
                    break;
                }
            }
        }
        return dp[dp.length - 1];
    }
}