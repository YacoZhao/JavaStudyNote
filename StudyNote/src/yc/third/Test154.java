package yc.third;

public class Test154 {
    /**
     * 使用二分查找来进行搜索
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        if(nums[0] < nums[nums.length - 1]){
            return nums[0];
        }else{
            int left = 0;
            int right = nums.length - 1;
            while(left <= right){
                int mid = left + (right - left + 1) / 2;
                if(mid > 0 && nums[mid] < nums[mid - 1]){
                    return nums[mid];
                }else if(nums[mid] >= nums[0]){
                    left = mid + 1;
                }else if(nums[mid] <= nums[nums.length - 1]){
                    right = mid - 1;
                }
            }
            return nums[0];
        }
    }
}
