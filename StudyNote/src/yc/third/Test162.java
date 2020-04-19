package yc.third;

public class Test162 {
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(nums[mid] > nums[mid + 1]){  //表示左侧一定有山峰
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }
}
