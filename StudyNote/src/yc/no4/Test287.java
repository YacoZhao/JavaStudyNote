package yc.no4;

import java.util.Arrays;

public class Test287 {
    /**
     * 解法一：
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            if(nums[i] == nums[i - 1]){
                return nums[i];
            }
        }
        return 0;
    }

    /**
     * 解法二：快慢指针
     * @param nums
     * @return
     */
    public int findDuplicate2(int[] nums) {
        int fasti = 0, slowi = 0;
        while(true){
            fasti=nums[fasti];
            fasti=nums[fasti];
            slowi=nums[slowi];
            if(slowi==fasti) break;
        }
        fasti=0;
        while(fasti!=slowi){
            fasti=nums[fasti];
            slowi=nums[slowi];
        }
        return slowi;
    }
}
