import java.util.ArrayList;
import java.util.List;

public class Q143 {
    /**
     * reorder linked list
     * 좋은 해결방법을 못찾아서 노가다로 풀었다.
     * solution은 2개 pointer를 사용하는 방법..
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // TODO(simon): indexed-approach > 30m > time 17, space 70
    public static void reorderList(ListNode head) {
        if (head.next == null)
            return;

        List<Integer> list = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            list.add(curr.val);
            curr = curr.next;
        }

        head.next = new ListNode(list.get(list.size() - 1));
        ListNode prev = head.next;

        for (int i = 1; i < list.size() / 2; ++i) {
            ListNode n = new ListNode(list.get(list.size() - i - 1));
            prev.next = new ListNode(list.get(i), n);
            prev = n;
        }

        if (list.size() % 2 == 1)
            prev.next = new ListNode(list.get(list.size() / 2));
    }

    public ListNode midNode(ListNode head){
        ListNode fast =  head, slow  =  head;
        while(fast.next!=null && fast.next.next!=null){
            fast =  fast.next.next;
            slow =  slow.next;
        }
        return slow;
    }

    public ListNode reverse(ListNode head){
        ListNode curr =  head, prev=  null, next = null;
        while(curr!=null){
            next  =  curr.next;
            curr.next =  prev ;
            prev =  curr;
            curr  =  next;
        }
        return prev;
    }

    // TODO(simon): solution > two pointers > time 100, space 60
    public void reorderList2(ListNode head) {
        ListNode midNode = midNode(head);
        ListNode nextToMid = midNode.next;
        midNode.next = null;
        ListNode p2 = reverse(nextToMid);

        ListNode p1 = head, p1Next;
        while(p1 != null && p2 != null){
            p1Next = p1.next;
            p1.next = p2;

            p1 = p2;
            p2=p1Next;
        }
    }

    public static void solve() {
        ListNode n4 = new ListNode(4);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(1, n2);

        reorderList(n1);

        ListNode curr = n1;
        while (curr != null) {
            System.out.println(curr.val);
            curr = curr.next;
        }
    }
}
