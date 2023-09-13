public class Q647 {
    /**
     * count all palindromic substring
     * palindrome의 특성을 잘 이해했으면 쉬운 문제
     */
    // TODO(simon): sliding window > 25m > time 95, space 55
    public int countSubstrings(String s) {
        int sum = 0;

        for (int i = 0 ; i < s.length() ; ++i) {
            sum += expandAroundCenter(s, i, i);
            sum += expandAroundCenter(s, i, i + 1);
        }

        return sum;
    }

    private int expandAroundCenter(String s, int left, int right) {
        int count = 0;

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count ++;
            left--;
            right++;
        }

        return count;
    }

    public void solve() {
        String s = "aaa";
        System.out.println(countSubstrings(s));
    }
}
