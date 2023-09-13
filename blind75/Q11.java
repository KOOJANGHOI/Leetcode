public class Q11 {
    /**
     * return maximum area from integer array
     * 전형적인 sliding window 문제. 참고만
     */
    public static int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int maxArea = 0;

        while (l < r) {
            int area = (r - l) * Math.min(height[l], height[r]);
            maxArea = Math.max(maxArea, area);

            if (height[l] > height[r]) {
                r--;
            } else {
                l++;
            }
        }

        return maxArea;
    }

    public static void solve() {
        int[] nums = { 1,8,6,2,5,4,8,3,7 };
        System.out.println(maxArea(nums));
    }
}
