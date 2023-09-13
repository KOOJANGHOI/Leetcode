public class Q53 {
    /**
     * return sum of sub-array with the largest sum
     * sliding window로 50분 걸렸는데 점수는 좋지 못함. 최적화가 안되었다.
     * sliding window, dp 모두 좋은 풀이법이 있는 문제
     */

    // sliding window > 0 ~ 50 (time 17, space 19)
    public static int maxSubArray(int[] nums) {
        if (nums.length == 1)
            return nums[0];

        int[] sums = new int[nums.length];
        int sum = 0;
        int maxDiff = -10000;

        for (int i = 0 ; i < nums.length ; ++i) {
            sum += nums[i];
            sums[i] = sum;
            maxDiff = Math.max(maxDiff, Math.max(nums[i], sum));
        }

        int l = 0, r = 1;

        while (r < nums.length) {
            int diff = sums[r] - sums[l];

            if (diff > 0) {
                maxDiff = Math.max(maxDiff, diff);
            } else {
                l = r;
            }

            r++;
        }

        return maxDiff;
    }

    // sliding window > solution (time 100, space 67)
    public int maxSubArray2(int[] nums) {
        int max = nums[0];
        int sum = 0;

        for (int i = 0 ; i < nums.length ; ++i) {
            if (sum < 0) {
                sum = 0;
            }

            sum += nums[i];
            max = Math.max(max, sum);
        }

        return max;
    }

    // dp > solution (time 17, space 97)
    public int maxSubArray3(int[] nums) {
        int[] dp = new int[nums.length];
        int max = nums[0];

        dp[0] = nums[0];

        for(int i = 1 ; i < nums.length ; ++i) {
            dp[i] = Math.max(dp[i-1]+nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public static void solve() {
        int[] nums = {-2, -1};
        System.out.println(maxSubArray(nums));
    }
}
