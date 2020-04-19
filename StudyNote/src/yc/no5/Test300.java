package yc.no5;

import java.util.Arrays;

public class Test300 {
    /**
     * 动态规划遍历
     * @param nums
     * @return
     */
    public int lengthOfLIS1(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return 1;
        int[] dp = new int[len];
        dp[len - 1] = 1;
        int ans = 0;
        for (int i = len - 2; i >= 0 ; i--) {
            if(nums[i] < nums[i + 1]){
                int max = 1;
                for(int j = i + 1; j < len ; j++){
                    if(nums[j] > nums[i]){
                        max = Math.max(max,dp[j]);
                    }
                }
                dp[i] = max + 1;
            }else{
                int temp = i + 1;
                while(temp < len && nums[i] >= nums[temp]){
                    temp++;
                }
                dp[i] = temp == len ? 1 : dp[temp] + 1;
            }
            ans = Math.max(ans,dp[i]);
        }
        return ans;
    }

    /**
     * 解法二： 动态规划+二分查找
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        if(nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int res = 0;
        Arrays.fill(dp, 1);
        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
