import java.util.HashMap;
import java.util.Map;

public class Q91 {
    /**
     * Given a string`s` containing only digits, return the number of ways to decode it. (encoding and decoding rules are given)
     * backtracking인줄 알았는데 점수가 안좋음.. 전형적인 dp 문제였다
     */
    public static int numDecodings(String s) {
        Map<Integer, Integer> result = new HashMap<>();
        return backtrack(s, 0, result);
    }

    // backtracking > time 28, space 33
    private static int backtrack(String s, int from, Map<Integer, Integer> result) {
        if (from == s.length())
            return 1;

        if (Integer.parseInt(s.substring(from, from + 1)) == 0)
            return 0;

        if (result.containsKey(from))
            return result.get(from);

        int totalWays = 0;

        totalWays += backtrack(s, from + 1, result);

        if (from < s.length() - 1) {
            int twoDigits = Integer.parseInt(s.substring(from, from + 2));

            if (twoDigits >= 10 && twoDigits <= 26) {
                totalWays += backtrack(s, from + 2, result);
            }
        }

        result.put(from, totalWays);

        return totalWays;
    }

    // dp > solution > time 95, space 88
    public int numDecodings2(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            int oneDigit = Integer.parseInt(s.substring(i - 1, i));
            int twoDigits = Integer.parseInt(s.substring(i - 2, i));

            if (oneDigit >= 1 && oneDigit <= 9) {
                dp[i] += dp[i - 1];
            }

            if (twoDigits >= 10 && twoDigits <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }

    public static void solve() {
        String s = "06";
        System.out.println(numDecodings(s));
    }
}
