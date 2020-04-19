package my_code_sum_up.code_dp;

import java.util.HashMap;

// 打家劫舍
// 方法一： 暴力递归（超时）
// 方法二： 动态规划
public class Test198 {

    // 暴力递归求解
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int len = nums.length;
        return getRes(nums,0,len);
    }

    private int getRes(int[] nums, int c, int len) {
        if(c >= len) return 0;
        return Math.max(nums[c] + getRes(nums, c + 2, len), getRes(nums, c + 1, len));
    }

    // 改动态规划
    public int rob2(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int len = nums.length;
        int[] dp = new int[len + 1];
        dp[len - 1] = nums[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 2] + nums[i],dp[i + 1]);
        }
        return dp[0];
    }
}
