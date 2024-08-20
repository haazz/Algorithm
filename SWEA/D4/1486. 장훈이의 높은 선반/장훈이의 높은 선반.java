import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// DFS 완전탐색을 통해 풀었습니다.
class Solution {

    static int[] people;
	static int n;
	static int k;
	static int min;
	
	public static void dfs(int idx, int sum) {	
		if(sum >= k) {
			min = Math.min(sum, min);
			return;
		}
        if (idx == n) {
            if (sum >= k)
                min = Math.min(sum, min);
            return;
        }
        // 현 idx를 더한 경우와 안 더한 경우를 확인
		dfs(idx+1, sum+people[idx]);
		dfs(idx+1, sum);
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
            people = new int[n];
			
			min = 0;
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				people[i] = Integer.parseInt(st.nextToken());
				min += people[i];
			}
			min++;
			dfs(0, 0);
			System.out.println("#" + t + " " + (min - k));
		}
	}
}