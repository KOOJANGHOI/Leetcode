public class Q5 {
    // TODO(simon): sliding window > 45m > stupid > palindrome's length can be odd/even ...
    // TODO(simon): sliding window > 15m > stupid > time limit exceeds (44/141)
    public String longestPalindrome(String s) {
        String max = s.substring(0,1);

        int mid = 1;

        while (mid < s.length() - 1) {
            int stride = Math.min(mid, s.length() - 1 - mid);
            String palindrome = s.substring(mid, mid + 1);

            System.out.println("mid index = " + mid + " , stride = " + stride + " , palindrome = " + palindrome);

            for (int i = 1 ; i <= stride ; ++i) {
                if (s.charAt(mid - i) == s.charAt(mid + i)) {
                    palindrome = s.substring(mid - i, mid + i + 1);
                    System.out.println("new palindrome = " + palindrome);

                    if (max.length() < palindrome.length())
                        max = palindrome;
                } else {
                    break;
                }
            }

            if (palindrome.length() == 1) {
                mid++;
            } else {
                mid += palindrome.length() / 2;
            }
        }

        return max;
    }

    // TODO(simon): solution > time 89, space 88
    public String longestPalindrome2(String s) {
        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i); // find palindrome from [i,i]
            int len2 = expandAroundCenter(s, i, i + 1); // find palindrome from [i,i+1]

            int len = Math.max(len1, len2);

            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public void solve() {
        String s = "babad";
        System.out.println(longestPalindrome2(s));
    }
}
