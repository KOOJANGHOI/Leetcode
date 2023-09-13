public class Q206 {
    /**
     * make reverse linked list of node
     * 10분 예상했는데 30분 소요. recursive한 쉬운 문제
     */

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // best > linked list > time 100, space 95
    public static ListNode reverseList(ListNode head) {
        if (head == null)
            return null;

        ListNode prev = head;
        ListNode curr = prev.next;

        while(curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        head.next = null;
        return prev;
    }

    // solution > linked-list > time 100, space 98
    public static ListNode reverseList2(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }

        return prev;
    }

    public static void solve() {
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);

        ListNode result = reverseList(node1);
        System.out.println(result.val);
        ListNode curr = result;
        System.out.println(curr.next);
        while(curr.next != null) {
            System.out.println(curr.next.val);
            curr = curr.next;
        }
    }
}
