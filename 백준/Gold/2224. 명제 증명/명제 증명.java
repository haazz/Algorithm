import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static HashMap<Character, PriorityQueue<Character>> graph = new HashMap<>();
    static HashSet<Character> nodeList = new HashSet<>();
    static PriorityQueue<char[]> result = new PriorityQueue<>((o1, o2) -> {
        if (o1[0] != o2[0]) {
            return o1[0] - o2[0];
        }
        return o1[1] - o2[1];
    });

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            String[] splitString = br.readLine().split(" => ");
            char p = splitString[0].charAt(0);
            char q = splitString[1].charAt(0);
            if (!graph.containsKey(p)) {
                graph.put(p, new PriorityQueue<>());
            }
            if (!graph.containsKey(q)) {
                graph.put(q, new PriorityQueue<>());
            }
            nodeList.add(p);
            nodeList.add(q);
            if (p != q && !graph.get(p).contains(q)) {
                result.add(new char[] { p, q });
            }

            graph.get(p).add(q);
        }

        for (char node1 : nodeList) {
            for (char node2 : nodeList) {
                for (char node3 : nodeList) {
                    if (node2 == node3 || graph.get(node2).contains(node3)) {
                        continue;
                    }
                    if (graph.get(node2).contains(node1) && graph.get(node1).contains(node3)) {
                        // System.out.println(node1 + " " + node2 + " " + node3);
                        result.add(new char[] { node2, node3 });
                        graph.get(node2).add(node3);
                    }
                }
            }
        }

        int cnt = 0;
        while (!result.isEmpty()) {
            char[] line = result.poll();
            sb.append(line[0]);
            sb.append(" => ");
            sb.append(line[1]);
            sb.append("\n");
            cnt++;
        }
        // for (char node1 : nodeList) {
        // PriorityQueue<Character> pq = graph.get(node1);
        // while (!pq.isEmpty()) {
        // sb.append(node1);
        // sb.append(" => ");
        // sb.append(pq.poll());
        // sb.append("\n");
        // cnt++;
        // }
        // // for (char node2 : graph.get(node1)) {
        // // sb.append(node1);
        // // sb.append(" => ");
        // // sb.append(node2);
        // // sb.append("\n");
        // // cnt++;
        // // }
        // }
        System.out.println(cnt);
        System.out.print(sb);

    }
}