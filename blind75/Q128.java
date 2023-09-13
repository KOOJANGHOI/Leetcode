import java.util.HashSet;

public class Q128 {
    /**
     * Given an unsorted array of integers nums, return *the length of the longest consecutive elements sequence. (time limit O(N))*
     * dfs인데 풀이 방법이 재밌는 문제. 최적화에 실패해서 풀지는 못함
     */
    // indexed approach > 30m > Memory Limit Exceeded > 67/73
    public static int longestConsecutive(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int size = max - min + 1;
        boolean[] marked = new boolean[size];

        for (int num : nums) {
            marked[size - 1 - max + num] = true;
        }

        int result = 0;
        int curr = 0;

        for (int i = min ; i <= max ; ++i) {
            if (marked[size - 1 - max + i]) {
                result = Math.max(result, ++curr);
            } else {
                curr = 0;
            }
        }

        return result;
    }

    // dfs > 45m > Time Limit Exceeded > 69/73
    public static int longestConsecutive2(int[] nums) {
        int max = 0;
        int[][] dp = new int[nums.length][2];

        for (int i = 0 ; i < nums.length ; ++i) {
            dp[i][0] = dfs(dp, nums, i, 0);
            dp[i][1] = dfs(dp, nums, i, 1);

            max = Math.max(max, 1 + dp[i][0] + dp[i][1]);
        }

        return max;
    }

    private static int dfs(int[][] dp, int[] nums, int targetIndex, int direction) {
        if (dp[targetIndex][direction] != 0)
            return dp[targetIndex][direction];

        for (int i = 0 ; i < nums.length ; ++i) {
            if (nums[i] == nums[targetIndex] + (direction == 0 ? direction - 1 : direction)) {
                dp[targetIndex][direction] =  1 + dfs(dp, nums, i, direction);
            }
        }

        return dp[targetIndex][direction];
    }

    // solution > dfs > time 27, space 17
    public static int longestConsecutive3(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int max = 0;

        for (int num : nums) {
            if (!set.contains(num - 1)) {
                max = Math.max(max, dfs(set, num));
            }
        }

        return max;
    }

    private static int dfs(HashSet<Integer> set, int num) {
        int currNum = num;
        int localMax = 1;

        while (set.contains(currNum + 1)) {
            currNum++;
            localMax++;
        }

        return localMax;
    }

    public static void solve() {
        int[] nums = {0,3,7,2,5,8,4,6,0,1};
        System.out.println(longestConsecutive(nums));
    }
}
