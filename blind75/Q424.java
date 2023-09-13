import java.util.*;

public class Q424 {
    /**
     * find longest repeating character with k-times replacement
     * dp로 생각했는데, sliding window인줄 몰라서 많이 헤맸다. 참신한 풀이법
     */
    // indexed-approach > 100m > time 5, space 7
    public int characterReplacement(String s, int k) {
        HashMap<Character, List<Integer>> map = new HashMap<>();
        int max = 0;

        for (int i = 0 ; i < s.length() ; ++i) {
            char c = s.charAt(i);

            if (map.containsKey(c)) {
                List<Integer> n = new ArrayList<>(map.get(c));
                n.add(i);
                map.put(c, n);
            } else {
                map.put(c, Collections.singletonList(i));
            }
        }

        for (char c : map.keySet()) {
            List<Integer> idxList = map.get(c);

            for (int i = 0 ; i < idxList.size() ; ++i) {
                int cnt = k;
                int from = idxList.get(i);
                int to = idxList.get(i);

                for (int j = i + 1 ; j < idxList.size() ; ++j) {
                    int end = idxList.get(j);

                    if (end == to + 1) {
                        to = end;
                    } else if (cnt > 0 && end - to - 1 <= cnt) {
                        cnt -= (end - to - 1);
                        to = end;
                    } else {
                        break;
                    }
                }

                while (cnt > 0 && to < s.length() - 1) {
                    to++;
                    cnt--;
                }

                while (cnt > 0 && from > 0) {
                    from--;
                    cnt--;
                }

                max = Math.max(max, to - from + 1);
            }
        }

        return max;
    }

    // solution > sliding window > time 75, space 89
    public int characterReplacement2(String s, int k) {
        int[] charCount = new int[26];
        int maxCount = 0; // Store the maximum character count within the sliding window
        int left = 0; // Left pointer of the sliding window
        int result = 0; // Length of the longest sub-string

        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            charCount[ch - 'A']++; // Increment character count

            // Update maxCount to keep track of the maximum character count within the window
            maxCount = Math.max(maxCount, charCount[ch - 'A']);

            // Calculate the number of replacements needed to make all characters in the window the same
            int replacements = (right - left + 1) - maxCount;

            // If the number of replacements needed is greater than k, then shrink the window from the left
            if (replacements > k) {
                charCount[s.charAt(left) - 'A']--; // Decrement character count
                left++; // Move the left pointer to the right
            }

            // Update the result with the length of the current window
            result = Math.max(result, right - left + 1);
        }

        return result;
    }

    public void solve() {
        String s = "ABCCCCC";
        int k = 2;

        System.out.println(characterReplacement(s,k));
    }
}
