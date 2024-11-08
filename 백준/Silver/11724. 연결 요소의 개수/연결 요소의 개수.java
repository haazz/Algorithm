import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int H;
    static int[][] board;
    static int[] unionFind;
    
    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        unionFind[a] = b;
    }

    public static int find(int x) {
        if (unionFind[x] == x) {
            return x;
        }
        return unionFind[x] = find(unionFind[x]);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        unionFind = new int[N];
        for (int i = 0; i < N; i++) {
            unionFind[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            union(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        }
    
        Set<Integer> connectElem = new HashSet<>();
        
        for (int i = 0; i < N; i++) {
            connectElem.add(find(i));
        }
        System.out.println(connectElem.size());
       
    }
}