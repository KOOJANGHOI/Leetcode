public class Q215 {

    public static void solve() {
        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        System.out.println(findKthLargest(nums, k));
    }

    public static int findKthLargest(int[] nums, int k) {
        int[] count = new int[20001];
        int total = 0;
        int answer = 0;

        for (int i = 0 ; i < nums.length; ++i) {
            count[nums[i] + 10000]++;
        }

        for (int i = count.length - 1 ; i >= 0; --i) {
            int num = count[i];

            if (total + num >= k) {
                answer = i - 10000;
                break;
            } else {
                total += num;
            }
        }

        return answer;
    }
}
