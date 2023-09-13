public class Q24 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode first = head;
        ListNode second = first.next;
        ListNode newHead = second == null ? first : second;
        ListNode previousFirst = null;

        while(second != null) {
            first.next = second.next;
            second.next = first;

            if (previousFirst != null) {
                previousFirst.next = second;
            }

            if (first.next == null) {
                break;
            } else {
                previousFirst = first;
                first = first.next;
                second = first.next;
            }
        }

        return newHead;
    }

    public void printLists (ListNode head) {
        ListNode next = head.next;

        System.out.println(head.val);

        while (next != null) {
            System.out.println(next.val);
            next = next.next;
        }
    }

    public void solve() {
        ListNode n4 = new ListNode(4);
        ListNode n3 = new ListNode(3,n4);
        ListNode n2 = new ListNode(2,n3);
        ListNode n1 = new ListNode(1,n2);

        printLists(swapPairs(n1));
    }
}
