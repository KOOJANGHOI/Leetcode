import java.util.Arrays;
import java.util.PriorityQueue;

public class Q347 {
    public class Num {
        int val;
        int cnt;

        Num(int val, int cnt) {
            this.val = val;
            this.cnt = cnt;
        }
    }

    /**
     * return k most frequent integers among integer array
     * 심플한 priority queue 문제. 참고
     */
    // TODO(simon): priority queue > 30m > time 100, space 99
    public int[] topKFrequent(int[] nums, int k) {
        int[] cnt = new int[20001];

        for (int n : nums) {
            cnt[n+10000]++;
        }

        PriorityQueue<Num> queue = new PriorityQueue<>((n1, n2) -> n2.cnt - n1.cnt);

        for (int i = 0 ; i < cnt.length ; i++) {
            if (cnt[i] > 0)
                queue.add(new Num(i-10000, cnt[i]));
        }

        int[] res = new int[k];

        int count = 0;

        while (!queue.isEmpty()) {
            if (count == k)
                break;

            res[count++] = queue.poll().val;
        }

        return res;
    }

    public void solve() {
        int[] nums = {1,1,1,2,2,3};
        int k = 2;

        System.out.println(Arrays.toString(topKFrequent(nums, k)));
    }
}
