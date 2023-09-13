public class Q1143 {
    static int[][] dp;

    public static int calc(String text1, String text2, int i, int j) {
        if (i < 0 || j < 0)
            return 0;

        if (dp[i][j] == -1) {
            if (i == 0 && j == 0)
                dp[i][j] = 0;

            dp[i][j] = text1.charAt(i) == text2.charAt(j) ? calc(text1, text2, i - 1, j - 1) + 1 : Math.max(calc(text1, text2, i, j - 1), calc(text1, text2, i - 1, j));
        }

        return dp[i][j];
    }

    // dp > top down > 25m > time 11, space 13
    public static int longestCommonSubsequence(String text1, String text2) {
        dp = new int[text1.length()][text2.length()];


        for (int i = 0 ; i < text1.length() ; ++i) {
            for (int j = 0 ; j < text2.length() ; ++j) {
                dp[i][j] = -1;
            }
        }

        return calc(text1, text2, text1.length() - 1, text2.length() - 1);
    }

    // dp > solution > bottom up > time 44, space 87
    public static int longestCommonSubsequence2(String text1, String text2) {
        int[][] dp2 = new int[text1.length() + 1][text2.length() + 1];


        for (int i = 1 ; i <= text1.length() ; ++i) {
            for (int j = 1 ; j <= text2.length() ; ++j) {
                if (text1.charAt(i -1) == text2.charAt(j - 1)) {
                    dp2[i][j] = dp2[i - 1][j -1] + 1;
                } else {
                    dp2[i][j] = Math.max(dp2[i][j -1], dp2[i - 1][j]);
                }
            }
        }

        return dp2[text1.length()][text2.length()];
    }

    public static void solve() {
        String text1 = "abcde", text2 = "ace";

        System.out.println(longestCommonSubsequence(text1, text2));
    }
}
