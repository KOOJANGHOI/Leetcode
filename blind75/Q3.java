import java.util.HashMap;

public class Q3 {
    /**
     * find the longest substring without repeating characters
     * LCS로 이해하고 풀었는데 memory limit exeeds .. sliding window로 쉽게 풀리는걸!
     */
    int[][] dp;

    // TODO(simon): dp > think 15m > impl 20m > Memory Limit Exceeded > 986 / 987
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0)
            return 0;

        int length = s.length();
        int max = 1;
        dp = new int[length][length];

        for (int i = 0 ; i < length ; ++i)
            dp[i][i] = 1;

        for (int i = 0 ; i < length ; ++i) {
            for (int j = i + 1 ; j < length ; ++j) {
                calc(s, i, j);
                max = Math.max(max, dp[i][j]);
            }
        }

        return max;
    }

    private int calc(String s, int i , int j) {
        if (dp[i][j] != 0)
            return dp[i][j];

        if (calc(s, i,j-1) == 0 || s.substring(i,j).contains("" + s.charAt(j))) {
            dp[i][j] = 0;
        } else {
            dp[i][j] = calc(s, i, j-1) + 1;
        }

        return dp[i][j];
    }

    // TODO(simon): solution > time 90, space 83
    public static int lengthOfLongestSubstring2(String s) {
        if (s.length() == 0) {
            return 0;
        }

        HashMap<Character, Integer> charToIdxMap = new HashMap<>();
        int maxLength = 0;
        int startIdx = 0;

        for (int endIdx = 0; endIdx < s.length(); endIdx++) {
            char currentChar = s.charAt(endIdx);

            if (charToIdxMap.containsKey(currentChar)) {
                // If the character is already in the map, update the startIndex
                // to the next index after the last occurrence of the current character.
                startIdx = Math.max(startIdx, charToIdxMap.get(currentChar) + 1);
            }

            charToIdxMap.put(currentChar, endIdx);
            maxLength = Math.max(maxLength, endIdx - startIdx + 1);
        }

        return maxLength;
    }

    public void solve() {
        String s = "anviaj";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
