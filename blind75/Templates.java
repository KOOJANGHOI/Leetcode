import java.util.*;

public class Templates {
    // binary search
    public int binarySearch(int[] nums, int key, int left, int right) {
        int middle = left + ((right - left) / 2);

        if (left > right)
            return -1;

        if (key == nums[middle])
            return middle;

        return key < nums[middle]
                ? binarySearch(nums, key, left, middle - 1)
                : binarySearch(nums, key, middle + 1, right);
    }

    // sliding window
    public static int slidingWindow(int[] prices) {
        int l = 0, r = 1;
        int mapProfit = 0;

        while (r < prices.length) {
            int profit = prices[r] - prices[l];

            if (profit > 0) {
                mapProfit = Math.max(mapProfit, profit);
            } else {
                l = r;
            }
            r++;
        }

        return mapProfit;
    }

    // backtracking
    // param : index (represent current space), current result (represent current accumulated answer)
    private static void backtrack(List<List<Integer>> result, List<Integer> combination, int[] candidates, int target, int start) {
        if (target == 0) {
            // stop condition
            result.add(new ArrayList<>(combination));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            }

            combination.add(candidates[i]);
            // recursion
            backtrack(result, combination, candidates, target - candidates[i], i);
            // restore current result
            combination.remove(combination.size() - 1);
        }
    }

    public void charToInt() {
        char upperChar = 'A';
        int upperInt = upperChar - 'A'; // 0 ~ 25

        char lowerChar = 'a';
        int lowerInt = lowerChar - 'a'; // 0 ~ 25

        char lowerUpperChar = 'a';
        int lowerUpperInt = lowerUpperChar - 'A'; // 0 ~ 67

        System.out.println(upperInt);
        System.out.println(lowerInt);
        System.out.println(lowerUpperInt);
    }

    public String findLongestPalindrome(String s) {
        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = findPalindromeWithExpand(s, i, i); // find palindrome from [i,i]
            int len2 = findPalindromeWithExpand(s, i, i + 1); // find palindrome from [i,i+1]

            int len = Math.max(len1, len2);

            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int findPalindromeWithExpand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
