import java.io.*;
import java.util.*;

public class Main {
	static int N, M, result[];
	static List<int[]>[] graph;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N + 1];
		result = new int[N + 1];
		Arrays.fill(result, Integer.MAX_VALUE);
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			graph[s].add(new int[] { e, weight });
			graph[e].add(new int[] { s, weight });
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		dijkstra(start);
		System.out.println(result[end]);
	}

	public static void dijkstra(int start) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

		pq.add(new int[] { start, 0 });
		result[start] = 0;

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();

			if (result[cur[0]] > cur[1]) {
				continue;
			}
			for (int[] next : graph[cur[0]]) {
				int newWeight = next[1] + result[cur[0]];
				if (newWeight < result[next[0]]) {
					result[next[0]] = newWeight;
					pq.add(new int[] { next[0], newWeight });
				}
			}

		}
	}

}
