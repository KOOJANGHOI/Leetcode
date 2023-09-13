import java.util.HashMap;

public class Q1 {
    // DP
    public static int[] getIndices(int[] nums, int target) {
        for (int i = 0 ; i < nums.length ; ++i) {
            int num1 = nums[i];
            for (int j = i + 1 ; j < nums.length ; ++j) {
                System.out.println(i + "," + j);
                int num2 = nums[j];

                if ((num1 + num2) == target) {
                    return new int[]{ i, j };
                }
            }
        }

        return null;
    }

    // HashMap
    public static int[] getIndices2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0 ; i < nums.length ; ++i) {
            int targetNum = target - nums[i];

            if (map.containsKey(targetNum)) {
                return new int[]{ i, map.get(targetNum) };
            } else {
                map.put(nums[i], i);
            }
        }

        return null;
    }

    public static void solve() {
        int[] nums= { 2,7,11,15 };
        int target = 9;
        int[] ans = getIndices2(nums, target);

        System.out.println(ans[0] + "," + ans[1]);
    }
}
