package yc.no4;

import org.omg.CORBA.INTERNAL;

public class Test209 {

    /**
     * 双指针
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        int left = 0;
        int right = 0;
        int len = nums.length;
        if(len == 0) return 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        while(right < len){
            sum += nums[right++];
            while(sum >= s){
                min = Math.min(min,right - left);
                sum -= nums[left];
                left++;
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
