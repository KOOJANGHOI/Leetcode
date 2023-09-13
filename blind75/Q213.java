public class Q213 {
    /**
     * 이전 문제 Q198의 응용인데, 쉬운 dp
     */
    // dp > 15m > time 100, space 40
    public static int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        int[][] dp = new int[nums.length][2];

        dp[0][0] = 0;
        dp[0][1] = nums[0];

        dp[1][0] = nums[1];
        dp[1][1] = nums[0];

        for (int i = 2 ; i < nums.length ; ++i) {
            dp[i][0] = Math.max(dp[i - 2][0] + nums[i], dp[i - 1][0]);
            dp[i][1] = i == nums.length - 1 ? Math.max(dp[i - 2][1], dp[i - 1][1]) : Math.max(dp[i - 2][1] + nums[i], dp[i - 1][1]);
        }

        return Math.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
    }

    // solution > dp > 15m > time 100, space 40
    public int rob2(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        // Rob houses from 0 to n-2
        int max1 = robHouses(nums, 0, nums.length - 2);

        // Rob houses from 1 to n-1
        int max2 = robHouses(nums, 1, nums.length - 1);

        return Math.max(max1, max2);
    }

    private int robHouses(int[] nums, int start, int end) {
        int prevMax = 0;
        int currMax = 0;

        for (int i = start; i <= end; i++) {
            int temp = currMax;
            currMax = Math.max(prevMax + nums[i], currMax);
            prevMax = temp;
        }

        return currMax;
    }

    public static void solve() {
        int[] nums = { 1,2,3 };
        System.out.println(rob(nums));
    }
}
