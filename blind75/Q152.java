public class Q152 {
    /**
     * return sub-array that has the largest product
     * 일반적인 dp 문제. 점수는 안좋은데 solution과 점수 차이는 근소한 수준
     */
    public static int maxProduct(int[] nums) {
        int[][] dp = new int[nums.length][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];

        int max = nums[0];

        for (int i = 1 ; i < nums.length ; ++i) {
            if (nums[i] >= 0) {
                dp[i][0] = Math.min(dp[i-1][0] * nums[i], nums[i]);
                dp[i][1] = Math.max(dp[i-1][1] * nums[i], nums[i]);
            } else {
                dp[i][0] = Math.min(dp[i-1][1] * nums[i], nums[i]);
                dp[i][1] = Math.max(dp[i-1][0] * nums[i], nums[i]);
            }

            max = Math.max(dp[i][1], max);
        }

        return max;
    }

    public static int maxProduct2(int[] nums) {
        int maxProduct = nums[0];
        int min = 1, max = 1;

        for (int i = 0 ; i < nums.length ; ++i) {
            int tMax = nums[i] * max;
            int tMin = nums[i] * min;

            max = Math.max(nums[i], Math.max(tMax, tMin));
            min = Math.min(nums[i], Math.min(tMax, tMin));

            maxProduct = Math.max(maxProduct, max);
        }

        return maxProduct;
    }

    public static void solve() {
        int[] nums = { 2,-5,-2,-4,3 };
        System.out.println(maxProduct2(nums));
    }
}
