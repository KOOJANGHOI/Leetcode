import java.util.*;

public class Q15 {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();

        int length = nums.length;
        Map<Integer, Integer> valueToIndex = new HashMap<>();

        for (int i = 0 ; i < length ; ++i ) {
            if (!valueToIndex.containsKey(nums[i]))
                valueToIndex.put(nums[i], i);
        }

        for (int i = 0 ; i < length ; ++i ) {
            for (int j = 0 ; j < length ; ++j ) {
                if (i < j) {
                    int sum = nums[i] + nums[j];

                    if (valueToIndex.containsKey(-sum)) {
                        int k = valueToIndex.get(-sum);

                        if (i != k && j != k) {
                            List<Integer> sub = new ArrayList<>();
                            sub.add(nums[i]);
                            sub.add(nums[j]);
                            sub.add(nums[k]);
                            Collections.sort(sub);

                            if (!answer.contains(sub)) {
                                answer.add(sub);
                            }
                        }
                    }
                }
            }
        }

        return answer;
    }

    public static List<List<Integer>> threeSum2(int[] nums) {
        HashSet<List<Integer>> set = new HashSet<>();
        Arrays.sort(nums);

        for (int i = 0 ; i < nums.length ; ++i ) {
            if (nums[i] > 0)
                return new ArrayList<>(set);

            int l = i + 1, r = nums.length - 1;

            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];

                if (sum == 0) set.add(new ArrayList<>(Arrays.asList(nums[i], nums[l], nums[r]))); i++; r--;
                if (sum < 0) l++;
                if (sum > 0) r--;
            }
        }

        return new ArrayList<>(set);
    }

    public static void solve() {
        int[] nums = { -2,0,1,1,2 };

        System.out.println(threeSum2(nums));
    }
}
