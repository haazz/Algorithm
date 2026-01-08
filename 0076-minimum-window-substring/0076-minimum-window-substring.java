class Solution {
    class Node {
        int idx;
        int val;
        Node next;
        Node prev;

        public Node(int idx, int val, Node prev) {
            this.idx = idx;
            this.val = val;
            this.next = null;
            this.prev = prev;
        }
    }
    public String minWindow(String s, String t) {
        int[] alp = new int[52];
        HashMap<Integer, Queue<Node>> hm = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            int val;
            if (t.charAt(i) >= 'a' && t.charAt(i) <= 'z') {
                val = t.charAt(i) - 'a';
                alp[val]++;
            } else {
                val = t.charAt(i) - 'A' + 26;
                alp[val]++;
            }
            hm.put(val, new LinkedList<>());
        }

        
        Node root = new Node(0, 0, null);
        Node end = new Node(0, 0, root);
        root.next = end;

        int minSize = Integer.MAX_VALUE;
        int minStartIdx = 0;
        int qSize = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int val = ch >= 'a' && ch <= 'z' ? ch - 'a' : ch - 'A' + 26;
            Queue<Node> q = hm.get(val);

            if (q == null) {
                continue;
            }
            

            if (q.size() < alp[val]) {
                Node nNode = new Node(i, val, end.prev);
                q.add(nNode);
                end.prev.next = nNode;
                end.prev = nNode;
                qSize++;
            } else {
                Node nNode = new Node(i, val, end.prev);
                Node pNode = q.poll();
                q.add(nNode);
                end.prev.next = nNode;
                end.prev = nNode;
                pNode.next.prev = pNode.prev;
                pNode.prev.next = pNode.next;
                
            }

            if (qSize >= t.length()) {
                int l = root.next.idx;
                int r = end.prev.idx;

                if (r - l < minSize) {
                    minStartIdx = l;
                    minSize = r - l;
                }
            }
        }
        return minSize == Integer.MAX_VALUE ? "" : s.substring(minStartIdx, minStartIdx + minSize + 1);
    }
}