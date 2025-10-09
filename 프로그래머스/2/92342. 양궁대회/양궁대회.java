// 쏜 횟수
// 점수

class Solution {
    final int N = 11;
    int[] answer;
    int maxScore = 0;
    
    int[] info;
    public void comb(int[] used, int i, int ps, int rs, int arrow) {
        if (rs - ps > maxScore) {
            answer = used.clone();
            maxScore = rs - ps;
        }
        if (i < 0) {
            return;
        }
        
        if (info[i] < arrow) {
            used[i] = info[i] + 1;
            if (info[i] != 0) {    
                comb(used, i - 1, ps - (N - i - 1), rs + (N - i) - 1, arrow - (info[i] + 1));
            } else {
                comb(used, i - 1, ps, rs + (N - i - 1), arrow - (info[i] + 1));
            }
            used[i] = 0;
        }
        comb(used, i - 1, ps, rs, arrow);
    }
    
    public int[] solution(int n, int[] info) {
        this.info = info;
        
        int ps = 0;
        for (int i = 0; i < info.length; i++) {
            if (info[i] > 0) {
                ps += N - i - 1;
            }
        }
        comb(new int[N], N - 2, ps, 0, n);
        if (answer == null) {
            return new int[] {-1};
        }
        int ua = 0;
        for (int i = 0; i < N; i++) {
            ua += answer[i];
        }
        if (ua < n) {
            answer[N - 1] = n - ua;
        }
        return answer;
    }
}