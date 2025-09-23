/**
    tree minheight == maxheight / 2 + 1 
 */

class Solution {
    Map<Integer, List<Integer>> tree = new HashMap<>();

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        int remainNode = n;
        int[] numEdges = new int[n];

        for (int i = 0; i < n; i++) {
            tree.put(i, new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            tree.get(edges[i][0]).add(edges[i][1]);
            tree.get(edges[i][1]).add(edges[i][0]);
            numEdges[edges[i][0]]++;
            numEdges[edges[i][1]]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (numEdges[i] == 1) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int qn = q.size();
            remainNode -= qn;
            if (remainNode == 0) {
                return new ArrayList<>(q);
            }

            for (; qn > 0; qn--) {
                int node = q.poll();

                for (int nNode : tree.get(node)) {
                    numEdges[nNode]--;
                    if (numEdges[nNode] == 1) {
                        q.add(nNode);
                    }
                }
            }
        }
        

        return new ArrayList<>(Arrays.asList(0));
    }
}