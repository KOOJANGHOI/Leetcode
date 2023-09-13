import java.util.Arrays;

public class Q238 {
    /**
     * return product array except self for given integer array
     * naive하게 dp로 풀면 time/memory limit exceeds, 참신하게 풀었는데 더 참신한 풀이가 있네 !!
     */
    // time 50%, space 7%
    public static int[] productExceptSelf(int[] nums) {
        int length = nums.length;

        int[] prefix = new int[length];
        int[] postfix = new int[length];
        int[] answer = new int[length];

        for (int i = 0 ; i < length ; ++i) {
            if (i == 0) {
                prefix[i] = nums[i];
            } else {
                prefix[i] = prefix[i-1] * nums[i];
            }
        }

        for (int i = length - 1 ; i >= 0 ; --i) {
            if (i == length - 1) {
                postfix[i] = nums[i];
            } else {
                postfix[i] = postfix[i+1] * nums[i];
            }
        }

        for (int i = 0 ; i < length ; ++i) {
            int preProduct = i == 0 ? 1 : prefix[i-1];
            int postProduct = i == length - 1 ? 1 : postfix[i+1];
            answer[i] = preProduct * postProduct;
        }

        return answer;
    }

    // time 100%, space 41%
    public static int[] productExceptSelf2(int[] nums) {
        int length = nums.length;
        int[] answer = new int[length];
        int pre = 1, post = 1;

        for (int i = 0 ; i < length ; ++i) {
            answer[i] = 1;
        }

        for (int i = 0 ; i < length - 1 ; ++i) {
            if (i == 0) {
                answer[i+1] = nums[i];
            } else {
                answer[i+1] *= pre * nums[i];
            }

            pre *= nums[i];
        }

        for (int i = length - 1 ; i > 0 ; --i) {
            if (i == length - 1) {
                answer[i-1] *= nums[i];
            } else {
                answer[i-1] *= post * nums[i];
            }

            post *= nums[i];
        }

        return answer;
    }



    public static void solve() {
        int[] nums= { 1,2,3,4 };
        System.out.println(Arrays.toString(productExceptSelf2(nums)));
    }
}
