public class Q33 {
    /**
     * advaned version of Q153
     */
    public static int search2(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;

        if (l == r)
            return nums[0] == target ? 0 : -1;

        while (l < r) {
            int m = (l + r) / 2;

            if (nums[l] == target)
                return l;

            if (nums[m] == target)
                return m;

            if (nums[r] == target)
                return r;

            if (nums[m] < nums[l] && nums[m] < nums[r]) {
                if (target < nums[r] && target > nums[m])
                    l = m + 1;
                else
                    r = m;
            } else if (nums[m] > nums[l] && nums[m] > nums[r]) {
                if (target > nums[r] && target < nums[m])
                    r = m;
                else
                    l = m + 1;
            } else {
                if (target < nums[m])
                    r = m;
                else
                    l = m + 1;
            }
        }

        return -1;
    }

    public static int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;

        if (l == r)
            return nums[0] == target ? 0 : -1;

        while (l < r) {
            int m = (l + r) / 2;

            if (nums[l] == target)
                return l;

            if (nums[m] == target)
                return m;

            if (nums[r] == target)
                return r;

            if (nums[m] < nums[r]) {
                if (target < nums[r] && target > nums[m]) {
                    l = m + 1;
                } else {
                    r = m;
                }
            } else {
                if (target > nums[r] && target < nums[m]) {
                    r = m;
                } else {
                    l = m + 1;
                }
            }
        }

        return -1;
    }

    public static void solve() {
        int[] nums = { 4,5,6,7,0,1,2 };
        int target = 5;

        System.out.println(search(nums, target));
    }
}
