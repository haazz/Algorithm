import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Main {
    static int N;
    static final int[] dy = {0, -1, 0, 1};
    static final int[] dx = {-1, 0, 1, 0};
    static class Pos {
        int y;
        int x;
        
        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        char[][] board = new char[N][N];
        List<Integer> nodes = new ArrayList<>();
        List<Integer> doors = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = s.charAt(j);
                if (board[i][j] == '#') {
                    doors.add(i * N + j);
                } else if (board[i][j] == '!') {
                    nodes.add(i * N + j);
                }
            }
        }
        nodes.add(0, doors.get(0));
        nodes.add(doors.get(1));
        int graphSize = nodes.size();

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> dijk = new HashMap<>();

        for (int i = 0; i < graphSize; i++) {
            graph.put(nodes.get(i), new HashSet<>());
            dijk.put(nodes.get(i), Integer.MAX_VALUE);
        }
    

        for (int i = 0; i < graphSize - 1; i++) {
            int y = nodes.get(i) / N;
            int x = nodes.get(i) % N;
            for (int d = 0; d < 4; d++) {
                for (int dist = 1; dist < N; dist++) {
                    int ny = dy[d] * dist + y;
                    int nx = dx[d] * dist + x;

                    if (ny < 0 || ny >= N || nx < 0 || nx >= N
                    || board[ny][nx] == '*') {
                        break;
                    }
                    if (board[ny][nx] == '!' || board[ny][nx] == '#') {
                        graph.get(y * N + x).add(ny * N + nx);
                        graph.get(ny * N + nx).add(y * N + x);
                    }
                }
            }
        }

        // System.out.println(graph);

        dijk.put(doors.get(0), 0);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[] { nodes.get(0), 0 });
        
        while (!pq.isEmpty()) {
            int[] elem = pq.poll();
            // System.out.println(elem[0]);
            if (dijk.get(elem[0]) < elem[1]) {
                continue;
            }
            for (int node : graph.get(elem[0])) {
                // System.out.println(elem[0] + " " + node);
                int mirrorCnt = elem[1] + 1;

                if (mirrorCnt < dijk.get(node)) {
                    dijk.put(node, mirrorCnt);
                    pq.add(new int[] { node, mirrorCnt });
                }
            }
        }

        // for (int i = 0; i < graphSize; i++) {
        //     System.out.print(dijk.get(nodes.get(i)) + " ");
        // }
        // System.out.println("");

        System.out.println(dijk.get(doors.get(1)) - 1);
    }
}
