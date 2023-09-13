import java.util.Arrays;
import java.util.Comparator;

public class Q435 {
    /**
     * count maximum non-overlapping intervals
     * 어렵지 않은 문제였는데 좀 복잡하게 생각해서 못풀었다.
     */
    // think > no dp, combination
    // greedy > 45m > 33/58
    public static int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        int[] curr = intervals[0];
        int count = 0;

        for (int i = 1 ; i < intervals.length ; ++i) {
            int[] next = intervals[i];

            if (next[0] < curr[1]) {
                if (next[1] - next[0] < curr[1] - curr[0]) {
                    curr = next;
                }
                count++;
            } else {
                curr = next;
            }
        }

        return count;
    }

    // solution > time 22, space 92
    public static int eraseOverlapIntervals2(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

        int[] curr = intervals[0];
        int count = 0;

        for (int i = 1 ; i < intervals.length ; ++i) {
            int[] next = intervals[i];

            if (next[0] < curr[1]) {
                count++;
            } else {
                curr = next;
            }
        }

        return count;
    }

    public static void solve() {
        int[][] intervals = {
                {-52,31},{-73,-26},{82,97},{-65,-11},{-62,-49},{95,99},{58,95},{-31,49},{66,98},{-63,2},{30,47},{-40,-26}
        };

        System.out.println(eraseOverlapIntervals(intervals));
    }
}
