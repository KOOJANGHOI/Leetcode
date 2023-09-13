public class Q55 {
    /**
     * check if you can jump array from start to end index. each element represent maximum jump length
     * backtracking 인줄 알아서 풀었는데, dp로 더 효율적으로 풀 수 있군..
     */

    // TODO(simon): backtracking > 30m > time 24, space 40
    public static boolean canJump(int[] nums) {
        int len = nums.length;
        boolean[] dp = new boolean[len];

        return backtrack(nums, dp, 1, len - 1);
    }

    public static boolean backtrack(int[] nums, boolean[] dp, int size, int to) {
        if (to == 0)
            return true;

        dp[to - size] = nums[to - size] >= size;

        if (dp[to - size])
            return backtrack(nums, dp, 1, to - size);

        for (int i = size + 1 ; i <= to ; i++) {
            if (backtrack(nums, dp, i, to))
                return true;
        }

        return false;
    }

    // TODO(simon): dp > 15m > time 21, space 54
    public static boolean canJump2(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;

        for (int i = 0 ; i < nums.length ; ++i) {
            if (!dp[i])
                break;

            for (int j = 1 ; j <= nums[i] ; ++j) {
                int target = i + j;

                if (target < nums.length && !dp[target]) {
                    if (target == nums.length - 1)
                        return true;

                    dp[target] = true;
                }
            }
        }

        return dp[nums.length - 1];
    }

    // solution > simple implementation > time 99, space 21
    public boolean canJump3(int[] nums) {
        int lastPos = nums.length - 1;

        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }

        return lastPos == 0;
    }

    public static void solve() {
        int[] nums = { 0,2,3 };
        System.out.println(canJump2(nums));
    }
}
