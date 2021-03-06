import java.util.Arrays;

public class FindMedianSortedArrays {

    /**
     * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
     *
     * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     *
     * 你可以假设 nums1 和 nums2 不会同时为空。
     *
     * 示例 1:
     *
     * nums1 = [1, 3]
     * nums2 = [2]
     *
     * 则中位数是 2.0
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums = concat(nums1, nums2);
        Arrays.sort(nums);
        if(nums.length%2 == 0){
            return (nums[(nums.length-1)/2] + nums[nums.length/2])/2.0;
        }else{
            return nums[nums.length/2];
        }
//        return nums.length%2 == 0 ? (nums[(nums.length-1)/2] + nums[nums.length/2])/2.0 : nums[nums.length/2];
    }

    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            int[] temp = nums2;
            nums2 = nums1;
            nums1 = temp;
        }

        int m = nums1.length;
        int n = nums2.length;

        int totalLeft = m + (n - m + 1) / 2;   // 防止m+n整形溢出等价于：(m + n + 1) / 2;
        int left = 0;
        int right = m;

        while (left < right) {
            int i = left + (right - left + 1) / 2;
            int j = totalLeft - i;
            // nums1[i-1] <= nums2[j] && nums1[i] >= nums2[j-1]
            if (nums1[i-1] > nums2[j]) {
                right = i - 1;
            } else {
                left = i;
            }
        }

        int i = left;
        int j = totalLeft - i;

        System.out.println("i:" + i + "\tj:" + j);

        int nums1LeftMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
        int nums1RightMin = i == m ? Integer.MAX_VALUE : nums1[i];
        int nums2LeftMax = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
        int nums2RightMin = j == n ? Integer.MAX_VALUE : nums2[j];

        if ((m + n) % 2 == 1) {
            return Math.max(nums1LeftMax, nums2LeftMax);
        } else {
            return (double) (Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin)) / 2;
        }
    }

    static int[] concat(int[] a, int[] b) {
        int[] c= new int[a.length+b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,4};
        int[] nums2 = {3,4};
        double d = FindMedianSortedArrays.findMedianSortedArrays2(nums1, nums2);
        System.out.println(d);
    }

}
