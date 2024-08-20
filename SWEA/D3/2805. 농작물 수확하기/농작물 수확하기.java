import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 단순히 for문을 사용하여 푸는 방식과 BFS를 사용하는 방식을 생각해 보았습니다.
// 둘 중 for문을 사용하여 풀어보았습니다.
public class Solution {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

		for (int tc=1; tc<=T; tc++) {
            int n = Integer.parseInt(br.readLine());
            int[][] map = new int[n][n];
            
            
            for (int i = 0; i < n; i++) {
                String line = br.readLine();

				for (int j=0; j<n; j++) {
					map[i][j] = line.charAt(j) - '0';
				}
			}
			
			int cnt = 0;
			int start = n/2;
			int end = n/2;
			for (int i=0; i<n; i++) {
                for (int j = start; j <= end; j++) {
                    cnt += map[i][j];
                }
                // 행의 절반을 기준으로 시작 지점과 끝 지점을 조정
				if (i < n/2) { 
					start -= 1; 
					end += 1;
				} else {
					start += 1;
					end -= 1;
				}
			}
			System.out.println("#" +tc + " " + cnt);
		}
	}
}