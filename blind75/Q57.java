import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Q57 {
    /**
     * merge all overlapping intervals after inserting one interval
     * Q56의 응용. 단순하게 풀었지만, 신박한 최적화 방법을 생각하지 못함
     */
    // simple impl > 60m > time 22 space 83
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        if (newInterval.length == 0)
            return intervals;

        if (intervals.length == 0)
            return new int[][]{newInterval};

        List<int[]> resultList = new ArrayList<>();
        int min = newInterval[0], max = newInterval[1];

        for (int[] itv : intervals) {
            if (newInterval[0] <= itv[1] && newInterval[1] >= itv[0]) {
                min = Math.min(min, itv[0]);
                max = Math.max(max, itv[1]);
            } else {
                resultList.add(itv);
            }
        }

        resultList.add(new int[]{min, max});

        resultList.sort(Comparator.comparingInt(arr -> arr[0]));

        int[][] result = new int[resultList.size()][2];

        for (int i = 0 ; i < resultList.size() ; ++i) {
            result[i] = resultList.get(i);
        }

        return result;
    }

    // solution > simple impl > time 22, space 83
    public static int[][] insert2(int[][] intervals, int[] newInterval) {
        if (newInterval.length == 0)
            return intervals;

        if (intervals.length == 0)
            return new int[][]{newInterval};

        List<int[]> resultList = new ArrayList<>();

        for (int i = 0 ; i < intervals.length ; ++i) {
            int[] itv = intervals[i];

            if (newInterval[1] < itv[0] || newInterval[0] > itv[1]) {
                resultList.add(itv);
            } else {
                newInterval[0] = Math.min(newInterval[0], itv[0]);
                newInterval[1] = Math.max(newInterval[1], itv[1]);
            }
        }

        resultList.add(newInterval);
        resultList.sort(Comparator.comparingInt(arr -> arr[0]));

        int[][] result = new int[resultList.size()][2];

        for (int i = 0 ; i < resultList.size() ; ++i) {
            result[i] = resultList.get(i);
        }

        return result;
    }

    // solution > simple impl > time 98, space 47
    public static int[][] insert3(int[][] intervals, int[] newInterval) {
        List<int[]> mergedIntervals = new ArrayList<>();
        int n = intervals.length;
        int i = 0;

        // Add intervals before the new interval that don't overlap
        while (i < n && intervals[i][1] < newInterval[0]) {
            mergedIntervals.add(intervals[i]);
            i++;
        }

        // Merge intervals that overlap with the new interval
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        // Add the merged interval
        mergedIntervals.add(newInterval);

        // Add remaining intervals after the new interval that don't overlap
        while (i < n) {
            mergedIntervals.add(intervals[i]);
            i++;
        }

        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }

    // simple impl > time 98, space 47
    public static int[][] insert4(int[][] intervals, int[] newInterval) {
        if (newInterval.length == 0)
            return intervals;

        if (intervals.length == 0)
            return new int[][]{newInterval};

        List<int[]> resultList = new ArrayList<>();

        for (int[] itv : intervals) {
            if (itv[1] < newInterval[0]) {
                resultList.add(itv);
            } else if (newInterval[1] >= itv[0]) {
                newInterval[0] = Math.min(newInterval[0], itv[0]);
                newInterval[1] = Math.max(newInterval[1], itv[1]);
            }
        }

        resultList.add(newInterval);

        for (int[] itv : intervals) {
            if (itv[0] > newInterval[1])
                resultList.add(itv);
        }

        return resultList.toArray(new int[resultList.size()][]);
    }

    public static void solve() {
        int[][] intervals = {{1,5}};
        int[] newInterval = {6,8};

        for (int[] itv : insert2(intervals, newInterval)) {
            System.out.println("[" + itv[0] + "," + itv[1] + "],");
        }

        System.out.println(insert(intervals, newInterval));
    }
}
