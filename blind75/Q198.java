public class Q198 {
    /**
     * 문제 설명은 길지만, 쉬운 dp 문제
     */
    // dp > 10m > time 100, space 51
    public static int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2 ; i < nums.length ; ++i) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[nums.length - 1];
    }

    public static void solve() {
        int[] nums = { 2,7,9,3,1 };
        System.out.println(rob(nums));
    }
}
