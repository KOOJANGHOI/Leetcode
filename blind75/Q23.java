import java.util.PriorityQueue;

public class Q23 {
    /**
     * Merge all the sorted linked-lists into one sorted linked-list and return it.*
     * priority queue로 풀었는데 역시 출제 의도는 divide & conquer 였지 .. 숙지하자
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // TODO(simon): priority queue > 5m > time 78, space 91
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0)
            return null;

        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>((n1, n2) -> n1.val - n2.val);

        for (ListNode n : lists) {
            if (n != null)
                queue.add(n);
        }

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

    // TODO(simon): solution > divide & conquer > time 100, space 85
    public static ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        return mergeLists(lists, 0, lists.length - 1);
    }

    private static ListNode mergeLists(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }

        int mid = start + (end - start) / 2;
        ListNode left = mergeLists(lists, start, mid);
        ListNode right = mergeLists(lists, mid + 1, end);

        return mergeTwoLists(left, right);
    }

    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
        ListNode n3 = new ListNode(5);
        ListNode n2 = new ListNode(4, n3);
        ListNode n1 = new ListNode(1, n2);

        ListNode n6 = new ListNode(4);
        ListNode n5 = new ListNode(3, n6);
        ListNode n4 = new ListNode(1, n5);

        ListNode n8 = new ListNode(6);
        ListNode n7 = new ListNode(2, n8);

        ListNode[] lists = { n1, n4, n7 } ;
        ListNode m = mergeKLists(lists);

        while (m != null) {
            System.out.println(m.val);
            m = m.next;
        }
    }
}
