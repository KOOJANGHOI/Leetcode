public class Q62 {
    /**
     * return possible unique path to *bottom-right corner or grid from top-left corner. moving can be right or down*
     *  쉬운 dp 문제인데, dfs로도 가능해서 재미있는 문제
     */
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        for (int i = 0 ; i < m ; ++i) dp[i][0] = 1;
        for (int j = 0 ; j < n ; ++j) dp[0][j] = 1;

        for (int i = 1 ; i < m ; ++i) {
            for (int j = 1 ; j < n ; ++j) {
                dp[i][j] = dp[i][j-1] + dp[i-1][j];
            }
        }


        return dp[m-1][n-1];
    }

    // dfs > solution > time 100, space 51
    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];
        return dfs(0, 0, m, n, dp);
    }

    private int dfs(int row, int col, int m, int n, int[][] dp) {
        if (row == m - 1 && col == n - 1) {
            return 1;
        }

        if (row >= m || col >= n) {
            return 0;
        }

        if (dp[row][col] != 0) {
            return dp[row][col];
        }

        int right = dfs(row, col + 1, m, n, dp);
        int down = dfs(row + 1, col, m, n, dp);

        dp[row][col] = right + down;

        return dp[row][col];
    }

    public static void solve() {
        int m = 3, n = 7;
        System.out.println(uniquePaths(m, n));
    }
}
