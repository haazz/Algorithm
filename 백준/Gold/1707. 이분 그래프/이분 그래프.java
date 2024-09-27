import java.io.*;
import java.util.*;

public class Main {
    static int V, E;
    static boolean[] visited;
    static int[] color;
    static List<Integer>[] graph;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            visited = new boolean[V + 1];
            color = new int[V + 1];
            graph = new ArrayList[V + 1];
            
            for (int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }
            
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                graph[s].add(e);
                graph[e].add(s);
            }
            
            boolean isBipartite = true;
            for (int i = 1; i <= V; i++) {
                if (!visited[i]) {
                    if (!dfs(i, 1)) {
                        isBipartite = false;
                        break;
                    }
                }
            }
            
            if (isBipartite) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
    
    public static boolean dfs(int index, int c) {
        visited[index] = true;
        color[index] = c;
        
        for (int neighbor : graph[index]) {
            if (!visited[neighbor]) {
                if (!dfs(neighbor, -c)) {
                    return false;
                }
            } else if (color[neighbor] == color[index]) {
                return false;
            }
        }
        
        return true;
    }
}
