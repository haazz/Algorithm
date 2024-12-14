import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static List<Integer>[] tree;
    static StringBuilder sb = new StringBuilder();

    public static void preorder(int node) {
        sb.append((char) ('A' + node));
        for (int nNode : tree[node]) {
            if (nNode == '.' - 'A') {
                continue;
            }
            preorder(nNode);
        }
    }

    public static void mid(int node) {
        int cnt = 0;

        for (int nNode : tree[node]) {
            if (cnt == 1) {
                sb.append((char) ('A' + node));
            }
            cnt++;
            if (nNode == '.' - 'A') {
                continue;
            }
            mid(nNode);
        }
    }

    public static void post(int node) {
        for (int nNode : tree[node]) {
            if (nNode == '.' - 'A') {
                continue;
            }
            post(nNode);
        }
        sb.append((char) ('A' + node));
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = st.nextToken().charAt(0) - 'A';
            int child1 = st.nextToken().charAt(0) - 'A';
            int child2 = st.nextToken().charAt(0) - 'A';
            tree[parent].add(child1);
            tree[parent].add(child2);
        }

        preorder(0);
        sb.append("\n");
        mid(0);
        sb.append("\n");
        post(0);
        sb.append("\n");

        System.out.print(sb);
    }
}