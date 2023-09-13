public class Q153 {
    /**
     * find minimum number in rotated sorted integer array
     * binary search 문제인데 나는 좀 복잡하게 풀었다. 더 심플하게 풀 수 있군..
     */
    public int findMin2(int[] nums) {
        int l = 0;
        int r = nums.length - 1;

        while (l < r) {
            final int m = (l + r) / 2;
            if (nums[m] < nums[r])
                r = m;
            else
                l = m + 1;
        }

        return nums[l];
    }

    public static int findMin(int[] nums) {
        class BinarySearch {
            int search(int start, int end) {
                if (end - start == 1)
                    return Math.min(nums[start], nums[end]);

                int pivot = (start + end) / 2;

                if (pivot >= 1 && nums[pivot - 1] > nums[pivot]) {
                    return nums[pivot];
                } else if (pivot <= nums.length - 2 && nums[pivot] > nums[pivot + 1]) {
                    return nums[pivot + 1];
                } else {
                    if (nums[start] > nums[pivot] && nums[end] > nums[pivot]) {
                        return nums[start] > nums[end] ? search(start, pivot) : search(pivot, end);
                    } else {
                        return nums[start] > nums[end] ? search(pivot, end) : search(start, pivot);
                    }
                }
            }
        }

        if (nums.length == 1)
            return nums[0];

        return new BinarySearch().search(0, nums.length - 1);
    }

    public static void solve() {
        int[] nums = { 2,3,4,5,1 };
        System.out.println(findMin(nums));
    }
}
