public class Q125 {
    /**
     * validate if string is palindrome
     * palindrome 판단 문제인데, 처음에 쉽게 풀었다가 개선해서 점수가 좋아짐
     */
    // TODO(simon): indexed-approach > 15m > time 7, space 12
    public boolean isPalindrome(String s) {
        String ss = "";

        for (char c : s.toLowerCase().toCharArray()) {
            if ((c >= 48 && c <= 57) || (c >= 65 && c <= 90) || (c >= 97 && c <= 122))
                ss += c;
        }

        int left = 0;
        int right = ss.length() - 1;

        while (left < right) {
            if (ss.charAt(left) != ss.charAt(right))
                return false;

            left++;
            right--;
        }

        return true;
    }

    // TODO(simon): indexed-approach > 10m > time 98, space 83
    public boolean isPalindrome2(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            while (left < right && !isNumberOrCharacter(s.charAt(left)))
                left++;

            while (left < right && !isNumberOrCharacter(s.charAt(right)))
                right--;

            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right)))
                return false;

            left++;
            right--;
        }

        return true;
    }

    // TODO(simon): solution > time 39, space 52
    public boolean isPalindrome3(String s) {
        if (s == null) {
            return false;
        }

        // Remove all non-alphanumeric characters and convert to lowercase
        String cleanedString = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        // Use two pointers to check if it's a palindrome
        int left = 0;
        int right = cleanedString.length() - 1;

        while (left < right) {
            if (cleanedString.charAt(left) != cleanedString.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    private boolean isNumberOrCharacter(char c) {
        // 0~9, A-Z, a-z
        return (c >= 48 && c <= 57) || (c >= 65 && c <= 90) || (c >= 97 && c <= 122);
    }

    public void solve() {
        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome2(s));
    }
}
