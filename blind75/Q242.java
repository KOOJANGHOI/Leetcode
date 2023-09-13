import java.util.Arrays;

public class Q242 {
    /**
     * check two strings are anagram
     * 쉬운 문제. anagram은 character 정렬시 같은 값
     */
    // TODO(simon): indexed-approach > 5m > time 81, space 97
    public boolean isAnagram(String s, String t) {
        int[] cnt = new int[26];

        for (int i = 0 ; i < s.length() ; ++i) {
            cnt[s.charAt(i) - 'a']++;
        }

        for (int i = 0 ; i < t.length() ; ++i) {
            cnt[t.charAt(i) - 'a']--;
        }

        for (int j : cnt) {
            if (j != 0)
                return false;
        }

        return true;
    }

    // TODO(simon): solution > indexed-approach > time 92, space 52
    public boolean isAnagram2(String s, String t) {
        // Check if the lengths of both strings are equal
        if (s.length() != t.length()) {
            return false;
        }

        // Convert the strings to character arrays
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        // Sort both arrays
        Arrays.sort(sChars);
        Arrays.sort(tChars);

        // Compare the sorted arrays
        for (int i = 0; i < sChars.length; i++) {
            if (sChars[i] != tChars[i]) {
                return false;
            }
        }

        // If all characters match, the strings are anagrams
        return true;
    }

    public void solve() {
        String s = "anagram";
        String t = "nagaram";

        System.out.println(isAnagram(s,t));
    }
}
