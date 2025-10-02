class Solution {
    long height = 0;
    
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            String bin = toBin(numbers[i]);
            answer[i] = isTree(bin, 0, bin.length(), 0);
        }
        return answer;
    }
    
    public int isTree(String bin, int l, int r, int depth) {
        if (height <= depth) {
            return -1;
        }
        int node = (l + r) / 2;
        // System.out.println(bin + " " + node);
        
        int left = isTree(bin, l, node - 1, depth + 1);
        int right = isTree(bin, node + 1, r, depth + 1);
        char cur = bin.charAt(node);
        if (left == 0 || right == 0) {
            return 0;
        }
        if ((left == 1 || right == 1) && cur == '0') {
            return 0;
        }
        if (cur == '1') {
            return 1;
        }
        return -1;
    }
    
    public String toBin(long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(num % 2);
            num /= 2;
        }
        
        long x = 0;
        long n = 1;
        while (sb.length() > x) {
            x = (1 << n) - 1;
            n++;
        }
        height = n - 1;
        for (int i = sb.length(); i < x; i++) {
            sb.append(0);
        }
            
        return sb.reverse().toString();
    }
    
}