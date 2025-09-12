/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    List<Integer> answer = new ArrayList<>();
    int checkDepth = 0;

    public void dfs(TreeNode node, int depth) {
        if (node == null) {
            return;
        }
        if (depth >= checkDepth) {
            answer.add(node.val);
            checkDepth = depth + 1;
        }
 
        dfs(node.right, depth + 1);
        dfs(node.left, depth + 1);
    }

    public List<Integer> rightSideView(TreeNode root) {
        dfs(root, 0);
        return answer;
    }
}