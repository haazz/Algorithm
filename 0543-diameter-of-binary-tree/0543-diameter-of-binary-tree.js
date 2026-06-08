/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @return {number}
 */

var dfs = (node) => {
    if (node === null) {
        return [0, 0];
    }
    const [lv, lh] = dfs(node.left);
    const [rv, rh] = dfs(node.right);
    const h = Math.max(lh, rh) + 1;
    const v = lh + rh;
    return [Math.max(lv, rv, v), h];
}
var diameterOfBinaryTree = function(root) {
    const [v, h] = dfs(root);
    return v;
};