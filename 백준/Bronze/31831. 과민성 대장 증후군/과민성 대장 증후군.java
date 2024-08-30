import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long arr[] = new long[N];
		long cnt = 0;
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		if(arr[0]>=M)cnt++;
		if(arr[0] < 0) arr[0] = 0;
		for(int i=1;i<N;i++) {
			arr[i] += arr[i-1];
			if(arr[i] < 0) arr[i] = 0;
			if(arr[i] >= M)cnt++;
		}
		System.out.println(cnt);
	}

}
