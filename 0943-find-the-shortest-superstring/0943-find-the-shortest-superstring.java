class Solution {
    int N;
    int mLen; 
    String[][] dp;

    class Node {
        int node;
        int visit;
        String val;

        public Node(int node, int visit, String val) {
            this.node = node;
            this.visit = visit;
            this.val = val;
        }
    }

    public int comp(String a, String b) {
        
        for (int i = 1; i < a.length(); i++) {
            if (b.startsWith(a.substring(i))) {
                return a.length() - i;
            }
        }
        return 0;
    }

    public void bfs(int sx, int[][] graph, String[] words) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(sx, 1 << sx, words[sx]));
        dp[sx][1 << sx] = words[sx];

        while (!q.isEmpty()) {
            Node elem = q.poll();

            for (int i = 0; i < N; i++) {
                if ((elem.visit & (1 << i)) != 0) {
                    continue;
                }
                int nVisit = elem.visit | 1 << i;
                String ns = elem.val + words[i].substring(graph[elem.node][i]);
                if (dp[i][nVisit] != null && dp[i][nVisit].length() <= ns.length()) {
                    continue;
                }
                dp[i][nVisit] = ns;
                q.add(new Node(i, nVisit, ns));
            }
        }

    }

    public String shortestSuperstring(String[] words) {
        N = words.length;
        int[][] graph = new int[N][N];
        mLen = 0;
        int fVisit = (1 << N) - 1;
        dp = new String[N][fVisit + 1];
        
        // for (int i = 0; i < N; i++ ){
        //     for (int j = 0; j < fVisit + 1; j++) {
        //         dp[i][j] = Integer.MAX_VALUE;
        //     }
        // }

        for (int i = 0; i < N; i++ ){
            mLen += words[i].length();
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    continue;
                }
                graph[i][j] = comp(words[i], words[j]);
            }
        }

        for (int i = 0; i < N; i++) {
            bfs(i, graph, words);
        }
        
        for (int i = 1; i < N; i++) {
            if (dp[i][fVisit].length() > dp[i - 1][fVisit].length()) {
                dp[i][fVisit] = dp[i - 1][fVisit];
            }

            
        }

        // for (int i = 0; i < N; i++) {
        // for (int j = 0; j < fVisit; j++) {
        //         System.out.print(dp[i][j] + '\t');
        //     }
        //     System.out.println("");

        //     }

        return dp[N - 1][fVisit];
    }
}