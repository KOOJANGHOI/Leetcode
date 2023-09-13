public class Q19 {
    /**
     * remove n-th node from end of list
     * 난 simple 하게 풀었는데, solution은 생각 못한 풀이법이다
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // TODO(simon): simple > 15m > time 100, space 10
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode(0, head);
        int length = 0;

        ListNode curr = head;
        while (curr != null) {
            ++length;
            curr = curr.next;
        }

        ListNode prev = dummyHead;
        curr = head;
        while (curr != null) {
            if (length-- == n) {
                prev.next = curr.next;
                break;
            }

            prev = curr;
            curr = curr.next;
        }

        return dummyHead.next;
    }

    // TODO(simon): solution > time 100, space 42
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode fast = head, slow = head;
        for (int i = 0; i < n; i++) fast = fast.next;
        if (fast == null) return head.next;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    public static void solve() {
        ListNode n5 = new ListNode(5);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(1, n2);

        ListNode n7 = new ListNode(2);
        ListNode n6 = new ListNode(1, n7);
        ListNode curr = removeNthFromEnd(n6, 2);

        while (curr != null) {
            System.out.println(curr.val);
            curr = curr.next;
        }
    }
}
