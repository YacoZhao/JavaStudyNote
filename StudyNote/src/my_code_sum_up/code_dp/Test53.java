package my_code_sum_up.code_dp;

//最大子序和
// 方法一：动态规划
public class Test53 {

    /**
     * 方法一： 动态规划（用dp数组记录以某个位置的结束的最大子序和）
     * @param nums
     * @return
     */
    public static int maxSubArray1(int[] nums) {
        int ans = nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // 优化动态规划
    public static int maxSubArray2(int[] nums) {
        int ans = nums[0];
        int dp  = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp = Math.max(dp + nums[i], nums[i]);
            ans = Math.max(ans, dp);
        }
        return ans;
    }
}
