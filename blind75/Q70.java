public class Q70 {
    /**
     * return distinct ways to reach the top of staircase. can climb 1 or 2 steps for each time
     * 매우 쉬운 dp 문제
     */
    public static int climbStairs(int n) {
        int[] dp = new int[n+1];
        dp[0] = dp[1] = 1;

        for (int i = 2 ; i <= n ; ++i) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    public static void solve() {
        int num = 3;
        System.out.println(climbStairs(num));
    }
}
