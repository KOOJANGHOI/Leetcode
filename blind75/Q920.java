public class Q920 {
    // dp > 60m > stupid
    public static int numMusicPlaylists(int n, int goal, int k) {
        return createMusicPlaylists(n, goal, k) % 1000000007;
    }

    private static int createMusicPlaylists(int n, int goal, int k) {
        if (goal == 0)
            return 1;

        if (k <= 1)
            return n * createMusicPlaylists(n-k,goal-1,k);

        return n * createMusicPlaylists(n-1,goal-1,k-1);
    }

    // solution > dp > time 70, space 44
    public int numMusicPlaylists2(int n, int goal, int k) {
        int MOD = 1000000007;
        int L = goal, N = n, K = k;

        // Create a 2D array to store the number of playlists
        long[][] dp = new long[L + 1][N + 1];

        // Base cases
        dp[0][0] = 1;

        // Iterate over the length of playlists
        for (int i = 1; i <= L; i++) {
            for (int j = 1; j <= N; j++) {
                // Compute the number of playlists at position i, with j different songs
                dp[i][j] += dp[i - 1][j - 1] * (N - (j - 1));

                // If K < j, we can add any song from the previous K songs to the end of the playlist
                if (j > K) {
                    dp[i][j] += dp[i - 1][j] * (j - K);
                }

                // Take the modulo to prevent overflow
                dp[i][j] %= MOD;
            }
        }

        return (int) dp[L][N];
    }

    public static void solve() {
        System.out.println(numMusicPlaylists(2,3,0));
    }
}
