/**
    tree minheight == maxheight / 2 + 1 
 */

class Solution {
    Map<Integer, List<Integer>> tree = new HashMap<>();
    List<Integer> answer = new ArrayList<>();
    int maxHeight = 0;
    int[] heights;

    public int getHeight(int n, int start) {
        boolean[] visit = new boolean[n];
        Queue<int[]> q = new LinkedList<>();
        int maxNode = start;
        q.add(new int[] {start, 1});
        visit[start] = true;

        while (!q.isEmpty()) {
            int[] elem = q.poll();

            for (int nNode : tree.get(elem[0])) {
                if (visit[nNode]) {
                    continue;
                }
                visit[nNode] = true;
                q.add(new int[] {nNode, elem[1] + 1});
                heights[nNode] = Math.max(elem[1] + 1, heights[nNode]);
                if (maxHeight < elem[1] + 1) {
                    maxHeight = elem[1] + 1;
                    maxNode = nNode;
                }
            }
        }
        return maxNode;
    }

    public int findStart(int n) {
        boolean[] visit = new boolean[n];
        Queue<int[]> q = new LinkedList<>();
        int maxNode = 0;
        int height = 0;
        q.add(new int[] {0, 1});
        visit[0] = true;

        while (!q.isEmpty()) {
            int[] elem = q.poll();

            for (int nNode : tree.get(elem[0])) {
                if (visit[nNode]) {
                    continue;
                }
                visit[nNode] = true;
                q.add(new int[] {nNode, elem[1] + 1});
                if (height < elem[1] + 1) {
                    height = elem[1] + 1;
                    maxNode = nNode;
                }
            }
        }
        return maxNode;
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n <= 1) {
            answer.add(0);
            return answer;
        }
        for (int i = 0; i < edges.length; i++) {
            if (!tree.containsKey(edges[i][0])) {
                tree.put(edges[i][0], new ArrayList<>());
            }
            if (!tree.containsKey(edges[i][1])) {
                tree.put(edges[i][1], new ArrayList<>());
            }
            tree.get(edges[i][0]).add(edges[i][1]);
            tree.get(edges[i][1]).add(edges[i][0]);
        }

        int startNode = findStart(n);

        heights = new int[n];
        int endNode = getHeight(n, startNode);
        getHeight(n, endNode);
        int minHeight = maxHeight / 2 + 1;

        for (int i = 0; i < n; i++) {
            if (heights[i] == minHeight) {
                answer.add(i);
            }
        }
        return answer;
    }
}