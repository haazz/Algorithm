import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static long[] nums;
    static Node[] tree;

    static class Node {
        long minValue;
        long maxValue;

        public Node(long minValue, long maxValue) {
            this.minValue = minValue;
            this.maxValue = maxValue;
        }
    }

    public static Node initTree(int start, int end, int node) {
        if (start == end) {
            return tree[node] = new Node(nums[start], nums[start]);
        }
        int mid = (start + end) / 2;
        Node node1 = initTree(start, mid, node * 2);
        Node node2 = initTree(mid + 1, end, node * 2 + 1);
        return tree[node] = new Node(Math.min(node1.minValue, node2.minValue),
                Math.max(node1.maxValue, node2.maxValue));
    }

    public static Node getValue(int start, int end, int node, int left, int right) {
        if (start > right || end < left) {
            return new Node(Long.MAX_VALUE, Long.MIN_VALUE);
        }
        if (start >= left && end <= right) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        Node node1 = getValue(start, mid, node * 2, left, right);
        Node node2 = getValue(mid + 1, end, node * 2 + 1, left, right);
        return new Node(Math.min(node1.minValue, node2.minValue),
                Math.max(node1.maxValue, node2.maxValue));
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new long[N + 1];
        tree = new Node[4 * N];
        for (int i = 1; i <= N; i++) {
            nums[i] = Long.parseLong(br.readLine());
        }
        initTree(1, N, 1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            Node node = getValue(1, N, 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            sb.append(node.minValue);
            sb.append("\n");
        }
        System.out.print(sb);
    }
}