import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static final int[] dy = { 0, -1, 0, 1};
	static final int[] dx = { -1, 0, 1, 0};
	static int M;
	static int N;
	
	
	public static int bfs(int[][] board, int ci, int cj) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { ci, cj });
		board[ci][cj] = 1;
		int cnt = 1;
		
		while (!q.isEmpty()) {
			int[] elem = q.poll();
			for (int d = 0; d < 4; d++) {
				int ny = elem[0] + dy[d];
				int nx = elem[1] + dx[d];
				
				if (ny < 0 || ny >= M || nx < 0 || nx >= N
						|| board[ny][nx] != 0) {
					continue;
				}
				board[ny][nx] = 1;
				cnt++;
				q.add(new int[] { ny, nx });
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] board = new int[M][N];
		
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int oj = Integer.parseInt(st.nextToken());
			int maxi = Integer.parseInt(st.nextToken());
			int maxj = Integer.parseInt(st.nextToken());
			
			for (; i < maxi; i += 1) {
				for (int j = oj; j < maxj; j++) {
					
					board[i][j] = -1;
				}
			}
		}
		
		int islandCnt = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 0) {
					pq.add(bfs(board, i, j));
					islandCnt++;
				}
			}
		}
		
		System.out.println(islandCnt);
		while (!pq.isEmpty()) {
			System.out.print(pq.poll() + " ");
		}
	}
}
