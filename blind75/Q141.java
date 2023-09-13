public class Q141 {
    /**
     * determine if the linked list has a cycle in it
     * 예상하지 못한 풀이법임
     * next와 next.next로 비교하면 cycle을 찾을 수 있군
     */
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // solution > linked-list > time 100, space 72
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        return true;
    }

    public static void solve() {
        System.out.println(hasCycle(null));
    }
}
