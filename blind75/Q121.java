public class Q121 {
    /**
     * return maximum profit from list of stock price list
     * sliding window로 푸는것이 깔끔한데 나는 dp로 풀었음.. 시간은 많이 소요 (40분)
     */
    // DP
    public static int maxProfit(int[] prices) {
        int len = prices.length;
        int[] dp = new int[len];

        if (len == 1)
            return 0;

        dp[len-1] = 0;
        dp[len-2] = prices[len-2] <= prices[len-1] ? prices[len-1] - prices[len-2] : 0;

        if (len == 2)
            return dp[0];

        int answer = dp[len-2];

        for (int i = len - 3 ; i >= 0 ; --i) {
            dp[i] = Math.max(dp[i+1] + prices[i+1] - prices[i], dp[i+2] + prices[i+2] - prices[i]);
            dp[i] = Math.max(0, dp[i]);
            answer = Math.max(answer, dp[i]);
        }

        return answer;
    }

    // Sliding window
    public static int maxProfit2(int[] prices) {
        int l = 0, r = 1;
        int maxProfit = 0;

        while (r < prices.length) {
            int profit = prices[r] - prices[l];

            if (profit > 0) {
                maxProfit = Math.max(maxProfit, profit);
            } else {
                l = r;
            }
            r++;
        }

        return maxProfit;
    }

    public static void solve() {
        int[] prices= { 7, 1, 5, 3, 6, 4 };
        System.out.println(maxProfit2(prices));
    }
}
