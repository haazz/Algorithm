

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
    int[] preorder;
    int[] inorder;
    int target = 0;

    public TreeNode build(int left, int right) {
        TreeNode node = null;
        int i = left;
        for ( ; i < right; i++) {
                if (inorder[i] == preorder[target]) {
                    node = new TreeNode(preorder[target]);
                    target++;
                    break;
                }
        }

        if (node == null) {
            return node;
        }
        node.left = build(left, i);
        node.right = build(i + 1, right);
        return node;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
          this.preorder = preorder;
          this.inorder = inorder;
          return build(0, inorder.length);
    }
}