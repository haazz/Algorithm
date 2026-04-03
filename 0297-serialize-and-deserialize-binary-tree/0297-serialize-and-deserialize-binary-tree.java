/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()) {
            TreeNode node = q.poll();

            if (node == null) {
                sb.append("null,");
                continue;
            }
            sb.append(node.val).append(",");
            q.add(node.left);
            q.add(node.right);
        }
        sb.append("]");
        System.out.println(sb.toString());
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() <= 2) {
            return null;
        }
        String[] sData = data.substring(1, data.length() - 2).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(sData[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;

        while (!q.isEmpty()) {
            TreeNode node = q.poll();

            if (!sData[i].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(sData[i]));
                q.add(node.left);
            }
            i++;

            if (i < sData.length && !sData[i].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(sData[i]));
                q.add(node.right);
            }
            i++;
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));