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
    int min = Integer.MAX_VALUE;
    int max = 0;

    public boolean isBalanced(TreeNode root) {
        if (search(root, 0) == Integer.MAX_VALUE) {
            return false;
        }
        return true;
    }

    public int search(TreeNode node, int depth) {
        if (node == null) {
            return depth;
        }
        int left = search(node.left, depth + 1);
        int right = search(node.right, depth + 1);

        if (Math.abs(left - right) > 1) {
            return Integer.MAX_VALUE;
        }
        return Math.max(left, right);
    }
}