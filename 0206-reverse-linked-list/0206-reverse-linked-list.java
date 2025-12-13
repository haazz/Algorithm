/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    Queue<Integer> q = new LinkedList<>();

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        q.add(head.val);
        ListNode prev = head;
        reverseList(head.next);
        head.val = q.poll();
        return head;
    }
}