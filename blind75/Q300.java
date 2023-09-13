import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Q300 {
    // sort -> for loop (o(n)) > 15m > stupid
    // sliding window > 45m > stupid
    // heap > 45m > time 11, space 18
    static class Node {
        public int val, max;

        public Node (int val, int max) {
            this.val = val;
            this.max = max;
        }
    }

    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 1)
            return 1;

        PriorityQueue<Node> maxHeap = new PriorityQueue<Node>(new Comparator<Node>() {
            public int compare(Node a, Node b) {
                {
                    if (a.max < b.max) return 1;
                    if (a.max > b.max) return -1;
                    return 0;
                }
            }
        });

        int max = 1;
        maxHeap.add(new Node(nums[nums.length - 1], 1));

        for (int i = nums.length - 2 ; i >= 0 ; i--) {
            int curr = 1;
            int found = -1;

            Iterator<Node> iterator = maxHeap.iterator();
            while (iterator.hasNext()) {
                Node n = iterator.next();

                if (n.val > nums[i]) {
                    found = n.val;
                    curr = Math.max(curr, n.max + 1);
                }
            }

            maxHeap.add(new Node(nums[i], curr));
            System.out.println(nums[i] + "'s max = " + curr + " from " + found);
            max = Math.max(max, curr);
        }

        return max;
    }

    // solution > dp > O(n^2)
    public int lengthOfLIS2(int[] nums) {
        int[] dp = new int[nums.length];
        int maxLen = 0;

        for (int i = 0; i < nums.length; i++) {
            int maxSubsequenceLen = 0;

            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    maxSubsequenceLen = Math.max(maxSubsequenceLen, dp[j]);
                }
            }

            dp[i] = maxSubsequenceLen + 1;
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    // solution > patience sorting > O(nlog(n))
    public int lengthOfLIS3(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int x : nums) {
            int i = 0, j = size;
            while (i != j) {
                int m = (i + j) / 2;
                if (tails[m] < x)
                    i = m + 1;
                else
                    j = m;
            }
            tails[i] = x;
            if (i == size) ++size;
        }
        return size;
    }

    public static void solve() {
        int[] nums = { 1,3,6,7,9,4,10,5,6 };
        System.out.println(lengthOfLIS(nums));
    }
}
