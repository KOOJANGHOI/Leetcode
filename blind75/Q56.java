import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Q56 {
    /**
     * merge all overlapping intervals
     * 심플한 문제인데, 풀이에 따라 점수는 차이가 있네. 쉽지만 재미있는 문제
     */
    // simple > 13m > time 44, space 90
    public static int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();

        Arrays.sort(intervals, Comparator.comparingInt(arr -> arr[0]));

        int[] curr = intervals[0];

        for (int i = 1 ; i < intervals.length ; ++i) {
            int[] next = intervals[i];

            if (curr[0] <= next[1] && curr[1] >= next[0]) {
                curr[0] = Math.min(curr[0], next[0]);
                curr[1] = Math.max(curr[1], next[1]);
            } else {
                result.add(curr);
                curr = next;
            }
        }

        result.add(curr);

        return result.toArray(new int[result.size()][]);
    }

    // solution > simple > time 81, space 99
    public static int[][] merge2(int[][] intervals) {
        // Step 1: Sort intervals based on the start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // Step 2: Merge overlapping intervals
        List<int[]> mergedIntervals = new ArrayList<>();
        int[] currentInterval = intervals[0];
        mergedIntervals.add(currentInterval);

        for (int[] interval : intervals) {
            if (interval[0] <= currentInterval[1]) {
                // Overlapping intervals, update the end time
                currentInterval[1] = Math.max(currentInterval[1], interval[1]);
            } else {
                // Non-overlapping interval, add to the list
                currentInterval = interval;
                mergedIntervals.add(currentInterval);
            }
        }

        // Step 3: Convert the list to an array and return
        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }

    public static void solve() {
        int[][] intervals = {
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        };

        System.out.println(merge2(intervals));
    }
}
