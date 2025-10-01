class Solution {
    static final int MOD = 10007;

    public int solution(int n, int[] tops) {
        if (n == 0 || tops == null || tops.length == 0) return 0;

        // 초기값: nonEmpty는 코드상 항상 1로 시작
        int nonEmpty = 1 % MOD;
        int answer = (tops[0] == 0 ? 3 : 4) % MOD;

        for (int i = 1; i < n; i++) {
            int prevAnswer = answer;                 // 이미 MOD 범위
            if (tops[i] == 0) {
                // (answer * 3 - nonEmpty) % MOD
                answer = (int)((answer * 3L) % MOD); // 곱셈 직후 MOD
                answer = (answer - nonEmpty) % MOD;  // 뺄셈 후 MOD
            } else {
                // (answer * 4 - nonEmpty) % MOD
                answer = (int)((answer * 4L) % MOD);
                answer = (answer - nonEmpty) % MOD;
            }
            if (answer < 0) answer += MOD;           // 음수 보정
            nonEmpty = prevAnswer;                   // 이미 MOD 범위라 그대로 대입
        }
        return answer; // 이미 MOD 범위
    }
}
