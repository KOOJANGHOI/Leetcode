import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q139 {
    /**
     * check if a given string can be segmented into sequence of words in dictionary
     * lcs 인줄 알았는데 dp 였네. 시간은 꽤 걸림
     */
    // brute force > 30m > time 6, space 5
    public static boolean wordBreak(String s, List<String> wordDict) {
        if (s.length() == 0)
            return true;

        for (String word : wordDict) {
            if (s.contains(word)) {
                boolean found = true;

                for (String w : s.split(word)) {
                    found = found && wordBreak(w, wordDict);
                    if (!found) break;
                }

                if (found)
                    return true;
            }
        }

        return false;
    }

    // dp > solution > time 50, space 9
    public boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true; // Empty string is always valid

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    public static void solve() {
        String s = "aaaaaaa";
        List<String> wordDict = Arrays.asList("aaaa","aaa");

        System.out.println(wordBreak(s, wordDict));
    }
}
