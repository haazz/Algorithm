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
    int answer = -1;

    public int sol(TreeNode root, int k, int cnt) {
        if (root == null) {
            return cnt;
        }

        int l = sol(root.left, k, cnt);
        if (l == k - 1) {
            answer = root.val;
        }
        int r = sol(root.right, k, l + 1);
        return r;
    }

    public int kthSmallest(TreeNode root, int k) {
        sol(root, k, 0);
        return answer;       
    }
}