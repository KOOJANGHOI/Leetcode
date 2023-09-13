import java.util.PriorityQueue;

public class Q21 {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // TODO: priority queue > 25m > time 6, space 80
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>((n1,n2) -> n1.val - n2.val);

        queue.add(list1);
        queue.add(list2);

        ListNode head = new ListNode(0);
        ListNode curr = head;

        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            curr.next = node;
            curr = node;

            if (node.next != null) {
                queue.add(node.next);
            }
        }

        return head.next;
    }

    // TODO: solution > simple > time 100, space 12
    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0); // Dummy head node
        ListNode current = dummyHead; // Current node to build the merged list

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        // Append the remaining nodes of the non-empty list
        if (l1 != null) {
            current.next = l1;
        } else {
            current.next = l2;
        }

        return dummyHead.next; // Return the merged list (excluding the dummy head)
    }

    public static void solve() {
        ListNode n3 = new ListNode(4);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(1, n2);

        ListNode m3 = new ListNode(4);
        ListNode m2 = new ListNode(3, m3);
        ListNode m1 = new ListNode(1, m2);

        ListNode curr = mergeTwoLists(n1,m1);

        while(curr != null) {
            System.out.println(curr.val);
            curr = curr.next;
        }

        System.out.println(mergeTwoLists(n1,m1));
    }
}
