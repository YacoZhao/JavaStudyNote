package yc.first;


/**
 * （困难）
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
 *
 *
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Test4 {
    //数学推导+二分查找算法（经典）
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if(m>n){
            int[] temp = nums2;
            nums2 = nums1;
            nums1 = temp;
        }

        int left = 0;
        int mid = (m +  n + 1)/2;
        int right = m;
        while(left <= right){
            int i = (left + right)/2;
            int j = mid - i;
            if(i<right && nums2[j-1] > nums1[i] ){
                left = i + 1;
            }else if(i > left && nums1[i-1] > nums2[j]){
                right = i -1;
            }else{
                int maxLeft = 0;
                if(i == 0){
                    maxLeft = nums2[j -1];
                }else if(j == 0){
                    maxLeft = nums1[i-1];
                }else{
                    maxLeft = Math.max(nums1[i-1],nums2[j-1]);
                }
                if((m+n)%2 == 1){
                    return maxLeft;
                }

                int minRight= 0;
                if(i == m){
                    minRight = nums2[j];
                }else if(j == m){
                    minRight = nums1[i];
                }else{
                    minRight = Math.min(nums1[i],nums2[j]);
                }
                return (maxLeft + minRight) / 2.0;

            }
        }
        return 0.0;
    }
}
