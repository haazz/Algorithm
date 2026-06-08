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

var res;

var dfs = (node) => {
    if (node === null) {
        return 0;
    }
    const l = dfs(node.left);
    const r = dfs(node.right);
    res = Math.max(l + r, res);
    return Math.max(l, r) + 1;
}
var diameterOfBinaryTree = function(root) {
    res = 0;
    dfs(root);
    return res;
};