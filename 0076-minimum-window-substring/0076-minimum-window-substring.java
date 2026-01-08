class Solution {
    class Node {
        int idx;
        int val;

        public Node(int idx, int val) {
            this.idx = idx;
            this.val = val;
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

        
        Deque<Node> q = new LinkedList<>();
        int minSize = Integer.MAX_VALUE;
        int qSize = 0;
        String answer = "";

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int val = ch >= 'a' && ch <= 'z' ? ch - 'a' : ch - 'A' + 26;

            if (!hm.containsKey(val)) {
                continue;
            }

            if (hm.get(val).size() < alp[val]) {
                Node nNode = new Node(i, val);
                hm.get(val).add(nNode);
                q.add(nNode);
                qSize++;
            } else {
                Node nNode = new Node(i, val);
                Node pNode = hm.get(val).poll();
                pNode.val = -1;
                hm.get(val).add(nNode);
                q.add(nNode);
            }

            if (qSize >= t.length()) {
                while (!q.isEmpty() && q.peekFirst().val == -1) {
                    q.poll();
                }
                int l = q.peekFirst().idx;
                int r = q.peekLast().idx;

                if (r - l < minSize) {
                    answer = s.substring(l, r + 1);
                    minSize = r - l;
                }
            }
        }
        return answer;
    }
}