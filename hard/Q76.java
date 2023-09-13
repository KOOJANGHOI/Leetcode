import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Q76 {
    /**
     * find minimum window substring
     * 좀 어려운 sliding window 문제. 오래 걸렸는데 점수는 안좋았다
     */
    // TODO(simon): sliding window > think 15m > impl 75m > time 9, space 6
    public String minWindow(String s, String t) {
        int[] charMatch = new int[68];
        int[] charCount = new int[68];
        String minSubstring = s + "*";

        for (int i = 0 ; i < t.length() ; ++i)
            charMatch[t.charAt(i) - 'A']++;

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0 ; i < s.length() ; ++i) {
            if (t.contains("" + s.charAt(i))) {
                queue.add(i);
            }
        }

        int r = 0;

        while (!queue.isEmpty()) {
            int left = queue.poll();

            for (int right = r ; right < s.length() ; ++right) {
                char c = s.charAt(right);
                charCount[c - 'A']++;

                boolean isSubstring = true;
                for (int j = 0 ; j < 68 ; ++j) {
                    if (charMatch[j] > 0 && charCount[j] < charMatch[j]) {
                        isSubstring = false;
                        break;
                    }
                }

                r = right;

                if (isSubstring) {
                    String substring = s.substring(left, right + 1);

                    if (substring.length() < minSubstring.length())
                        minSubstring = substring;
                    break;
                }
            }

            charCount[s.charAt(left) - 'A']--;
            charCount[s.charAt(r) - 'A']--;
        }

        return minSubstring.length() == s.length() + 1 ? "" : minSubstring;
    }

    // TODO(simon): solution > sliding window > time 51, space 58
    public String minWindow2(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0 || s.length() < t.length()) {
            return "";
        }

        // Create a map to store the character frequencies of string T
        Map<Character, Integer> targetFreqMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetFreqMap.put(c, targetFreqMap.getOrDefault(c, 0) + 1);
        }

        int requiredChars = targetFreqMap.size(); // Number of unique characters in T

        int left = 0; // Window's left pointer
        int right = 0; // Window's right pointer
        int formedChars = 0; // Number of unique characters formed in the window

        // Create a map to store the character frequencies in the current window
        Map<Character, Integer> windowFreqMap = new HashMap<>();

        // Variables to store the starting and ending indices of the minimum window substring
        int start = 0;
        int end = -1;

        // Variable to track the length of the minimum window found so far
        int minWindowLength = Integer.MAX_VALUE;

        while (right < s.length()) {
            // Expand the window from the right side
            char c = s.charAt(right);
            windowFreqMap.put(c, windowFreqMap.getOrDefault(c, 0) + 1);

            // Check if the current character is in T and its frequency matches in the window
            if (targetFreqMap.containsKey(c) && windowFreqMap.get(c).intValue() == targetFreqMap.get(c).intValue()) {
                formedChars++;
            }

            // Contract the window from the left side until all characters in T are present
            while (left <= right && formedChars == requiredChars) {
                // Update the minimum window length and indices if a smaller window is found
                int currentWindowLength = right - left + 1;
                if (currentWindowLength < minWindowLength) {
                    minWindowLength = currentWindowLength;
                    start = left;
                    end = right;
                }

                // Shrink the window from the left side
                char leftChar = s.charAt(left);
                windowFreqMap.put(leftChar, windowFreqMap.get(leftChar) - 1);

                // Check if the left character is in T and its frequency is less than required
                if (targetFreqMap.containsKey(leftChar) && windowFreqMap.get(leftChar) < targetFreqMap.get(leftChar)) {
                    formedChars--;
                }

                left++; // Move the left pointer to the right
            }

            right++; // Move the right pointer to the right
        }

        // If no minimum window is found, return an empty string
        if (end == -1) {
            return "";
        }

        // Return the minimum window substring
        return s.substring(start, end + 1);
    }

    public void solve() {
        String s = "aabbbbbbbbbbabbbaaa";
        String t = "aaa";
        System.out.println(minWindow(s,t));
    }
}
