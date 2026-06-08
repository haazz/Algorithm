/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} head
 * @return {ListNode}
 */
var middleNode = function(head) {
    let mid = head;
    let node = head;
    let cnt = 1;
    let mIdx = 1;
    const res = [];

    while (node !== null) {
        let len = Math.round(cnt / 2);
        len = cnt % 2 === 0 ? len + 1 : len;
        while (mIdx < len) {
            mid = mid.next;
            mIdx++;
        }
        node = node.next;
        cnt++;
    }
    return mid;
};