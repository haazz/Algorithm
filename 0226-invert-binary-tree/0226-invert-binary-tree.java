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

    public void invert(TreeNode node) {
        if (node == null) {
            return;
        }
        invert(node.right);
        invert(node.left);
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;
    }

    public TreeNode invertTree(TreeNode root) {
        invert(root);
        return root;
    }
}