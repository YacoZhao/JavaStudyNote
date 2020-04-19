package my_code_sum_up.code_dp;

//152. 乘积最大子序列
// 方法一；暴力枚举（超时）
// 方法二：动态规划
public class Test152 {

    /**
     * 方法一：暴力枚举
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            ans = Math.max(ans,cur);
            for (int j = i + 1; j < nums.length; j++) {
                cur = cur * nums[j];
                ans = Math.max(ans,cur);
            }
        }
        return ans;
    }

    // 思路分析
    // 理清变量： dpMax[i] : 必须以nums[i]结尾的子数组，且乘积包含nums[i]的最大乘积和
    //           dpMin[i] : 必须以nums[i]结尾的子数组，且乘积包含nums[i]的最小乘积和
    // 情况一：当 nums[i] >= 0 并且dpMax[i-1] > 0，dpMax[i] = dpMax[i-1] * nums[i]
    // 情况二：当 nums[i] >= 0 并且dpMax[i-1] < 0，此时如果和前边的数累乘的话，会变成负数，所以dpMax[i] = nums[i]
    // 情况三：当 nums[i] < 0 且dpMin[i-1] < 0，dpMax[i] = dpMin[i-1] * nums[i]
    // 情况四: 当 nums[i] < 0 且dpMin[i-1] >= 0，dpMax[i] = nums[i]

    // 所以：
    // -- dpMax[i] = max(dpMax[i-1] * nums[i],dpMin[i-1] * nums[i],nums[i])
    // -- dpMin[i] = min(dpMax[i-1] * nums[i],dpMin[i-1] * nums[i],nums[i])

    // 构建规划如下
    public int maxProducts4(int[] nums) {
        int ans = nums[0];
        int[] dpMax = new int[nums.length];
        int[] dpMin = new int[nums.length];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dpMax[i] = Math.max(nums[i],Math.max(dpMax[i - 1] * nums[i], dpMin[i - 1] * nums[i]));
            dpMin[i] = Math.min(nums[i],Math.min(dpMax[i - 1] * nums[i], dpMin[i - 1] * nums[i]));
            ans = Math.max(ans,dpMax[i]);
        }
        return ans;
    }
    public int maxProducts5(int[] nums) {
        int ans = nums[0];
        int dpMax = nums[0];
        int dpMin = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int preMax = dpMax;
            dpMax = Math.max(nums[i],Math.max(dpMax * nums[i], dpMin * nums[i]));
            dpMin = Math.min(nums[i],Math.min(preMax * nums[i], dpMin * nums[i]));
            ans = Math.max(ans,dpMax);
        }
        return ans;
    }

}
