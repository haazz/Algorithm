import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 심사대 갯수
		int M = Integer.parseInt(st.nextToken()); // 인원 수
		
		long[] checkPannel = new long[N];
		
		long maxCheckTime = 0;
		
		long result = 0;
		
		for(int i=0; i<N; i++) {
			checkPannel[i] = Integer.parseInt(br.readLine());
			maxCheckTime = Math.max(maxCheckTime, checkPannel[i]);
		}
        
		// 걸리는 시간의 최대값은 가장 오래 걸리는 검사 시간 * 인원 수
        long highTime = M * maxCheckTime;
        long lowTime = 0;
        
        while(lowTime <= highTime) {
        	long mid = (highTime + lowTime) / 2;
        	
        	long count = 0; // mid시간 만큼 걸릴 때 통과되는 인원 세기
        	for(int i=0; i<N; i++) {
        		count += mid / checkPannel[i];
        		
        		if(count >= M) break; // 통과해야되는 인원을 채웠으면 그만 추가하고 탈출
        	}
        	
        	// 통과되는 인원 수에 따른 반 쪼개기
        	// 통과되는 인원이 내가 통과시키고자 하는 인원보다 같거나 많다면 적절 인원으로 줄이기 위해 최대 범위를 줄여야된다.
        	if(count >= M) {
        	   result = mid; // 이 조건에선 일단 모든 인원이 통과되며, 최소 범위가 최대 범위를 넘기직전 시간을 저장
        	   highTime = mid-1;
        	}
        	// 통과되는 인원이 내가 통과시키고자 하는 인원보다 적다면 적절 인원으로 늘리기 위해 최대 범위를 늘려야된다.
        	else lowTime = mid+1; 
        	
        }
        System.out.println(result);
	}
}