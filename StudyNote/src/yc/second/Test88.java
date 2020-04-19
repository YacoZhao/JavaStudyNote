package yc.second;

public class Test88 {
    /**
     *
     * public static void arraycopy​(Object src,
     *                              int srcPos,
     *                              Object dest,
     *                              int destPos,
     *                              int length)
     *
     * src - 源数组。
     * srcPos - 源数组中的起始位置。
     * dest - 目标数组。
     * destPos - 目的地数据中的起始位置。
     * length - 要复制的数组元素的数量。
     *
     *
     *
     * 双指针，从数组末端向前遍历
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int len1 = m - 1;
        int len2 = n - 1;
        int len = m + n - 1;
        while (len1 >= 0 && len2 >= 0) {
            nums1[len--] = nums1[len1] > nums2[len2] ? nums1[len1--] : nums2[len2--];
        }
        System.arraycopy(nums2, 0, nums1, 0, len2 + 1);
    }
}
