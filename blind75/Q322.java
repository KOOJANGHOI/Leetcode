import java.util.Arrays;

public class Q322 {
    /**
     * return the fewest number of coins that you need to make up that amount
     * 20줄도 안되는 전형적인 dp 문제인데 .. 좀 오래걸렸다
     */
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);

        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void solve() {
        int[] coins = { 186,419,83,408 };
        int amount = 6249;

        System.out.println(coinChange(coins, amount));
    }
}
