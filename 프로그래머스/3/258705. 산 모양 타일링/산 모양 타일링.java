class Solution {
    static final int MOD = 10007;

    public int solution(int n, int[] tops) {
        if (n == 0 || tops == null || tops.length == 0) return 0;

        int nonEmpty = 1;
        int answer = tops[0] == 0 ? 3 : 4;

        for (int i = 1; i < n; i++) {
            int prevAnswer = answer;
            if (tops[i] == 0) {
                answer = ((answer * 3) - nonEmpty + MOD) % MOD;
            } else {
                answer = ((answer * 4) - nonEmpty + MOD) % MOD;
            }
            nonEmpty = prevAnswer;
        }
        return answer;
    }
}
