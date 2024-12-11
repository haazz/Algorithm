import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, M, K;
    static long[] nums, tree;
    static final long MAX_VALUE = 1_000_000_007;

    public static long initTree(int start, int end, int node) {
        if (start == end) {
            return tree[node] = nums[start];
        }
        int mid = (start + end) / 2;
        return tree[node] = (initTree(start, mid, node * 2) * initTree(mid + 1, end, node * 2 + 1)) % MAX_VALUE;
    }

    public static long prod(int start, int end, int node, int left, int right) {
        if (start > right || end < left) {
            return 1;
        }
        if (start >= left && end <= right) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        return (prod(start, mid, node * 2, left, right) * prod(mid + 1, end, node * 2 + 1, left, right)) % MAX_VALUE;
    }

    public static long update(int start, int end, int node, int idx, long diff) {
        if (start > idx || end < idx) {
            return tree[node];
        }
        if (start >= end) {
            return tree[node] = diff;
        }
        int mid = (start + end) / 2;
        return tree[node] = (update(start, mid, node * 2, idx, diff)
                * update(mid + 1, end, node * 2 + 1, idx, diff)) % MAX_VALUE;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        nums = new long[N + 1];
        tree = new long[4 * N];

        for (int i = 1; i <= N; i++) {
            nums[i] = Long.parseLong(br.readLine());
        }
        initTree(1, N, 1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                update(1, N, 1, b, c);
                nums[b] = c;
            } else {
                sb.append(prod(1, N, 1, b, (int) c)).append("\n");
            }
        }
        System.out.print(sb);
    }
}