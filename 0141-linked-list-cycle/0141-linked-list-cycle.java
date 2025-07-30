/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    Set<ListNode> visit = new HashSet<>();

    public boolean hasCycle(ListNode head) {
        while (head != null) {
            if (visit.contains(head)) {
                return true;
            }
            visit.add(head);
            head = head.next;
        }
        return false;
    }
}