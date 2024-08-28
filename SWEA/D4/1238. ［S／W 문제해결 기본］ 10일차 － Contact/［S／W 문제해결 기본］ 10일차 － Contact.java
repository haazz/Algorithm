import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
    static int N;
    static int S;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int tc = 1; tc <= 10; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());
            // node 들을 array 내부에 있는 리스트로 저장
            List<Integer>[] nodes = new ArrayList[101];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 101; i++) {
                nodes[i] = new ArrayList<>();
            }

            for (int i = 0; i < N / 2; i++) {
                nodes[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
            }

            // bfs를 돌렸습니다.
            Queue<int[]> q = new LinkedList<>();
            int maxDepth = 0;
            int result = S;
            q.add(new int[] { S, 0 });
            boolean[] visited = new boolean[101];
            visited[S] = true;

            while (!q.isEmpty()) {

                int[] elem = q.poll();
                boolean isUpdate = false;

                // 방문하지 않은 노드에 대해서 q에 추가
                for (int node : nodes[elem[0]]) {
                    if (visited[node]) {
                        continue;
                    }
                    isUpdate = true;
                    visited[node] = true;
                    q.add(new int[] { node, elem[1] + 1 });
                }

                // 아무 노드도 방문하지 않았다면 끝 지점에 도착한 것이기 떄문에
                // 깊이를 확인해서 결과를 업데이트
                if (!isUpdate) {
                    if (maxDepth < elem[1]) {
                        maxDepth = elem[1];
                        result = elem[0];
                    } else if (maxDepth == elem[1]) {
                        result = Math.max(result, elem[0]);
                    }
                }
            }
            System.out.println("#" + tc + " " + result);
        }
    }
}