class Solution {
    
    public String toK(int num, int k) {
        StringBuilder sb = new StringBuilder();
        
        while (num > 0) {
            sb.append(num % k);
            num /= k;
        }
        return sb.reverse().toString();
    }
    
    public boolean isPrime(long num) {
        if (num <= 1) {
            return false;
        }
        for (long i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    public int solution(int n, int k) {
        String ns = toK(n, k);
        String[] ss = ns.split("0");
        
        int answer = 0;
        for (int i = 0; i < ss.length; i++) {
            if (ss[i].isBlank()) {
                continue;
            }
            if (isPrime(Long.parseLong(ss[i]))) {
                answer++;
            }
        }
        return answer;
    }
}