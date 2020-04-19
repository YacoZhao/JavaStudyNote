package yc.third;

public class Test198 {

    // 动态规划
    public int rob(int[] nums) {
        int[] dp = new int[nums.length + 1];
        int ans = 0;
        for (int i = 0; i <= nums.length; i++) {
            if(i == 1){
                dp[i] = nums[0];
            }else if(i > 1){
                dp[i] = Math.max(dp[i - 2] + nums[i -1],dp[i-1]);
            }
            ans = Math.max(ans,dp[i]);
        }
        return ans;
    }
}
