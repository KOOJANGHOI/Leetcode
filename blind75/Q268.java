public class Q268 {
    public static int missingNumber(int[] nums) {
        int n = nums.length;
        int total = n * (n + 1) / 2;
        int sum = 0;

        for (int i : nums) {
            sum += i;
        }

        return total - sum;
    }

    public static void solve() {
        int[] nums = { 9,6,4,2,3,5,7,0,1 };
        System.out.println(missingNumber(nums));
    }
}
