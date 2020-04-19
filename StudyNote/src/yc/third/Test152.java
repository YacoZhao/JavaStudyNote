package yc.third;

public class Test152 {

    // 暴力穷举
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

    // 动态规划
    public int maxProduct2(int[] nums) {
        int ans = nums[0];
        int[] dpMax = new int[nums.length];
        int[] dpMin = new int[nums.length];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dpMax[i] = Math.max(nums[i] * dpMax[i - 1],Math.max(nums[i] * dpMin[i - 1],nums[i]));
            dpMin[i] = Math.min(nums[i] * dpMax[i - 1],Math.min(nums[i] * dpMin[i - 1],nums[i]));
            ans = Math.max(ans,dpMax[i]);
        }
        return ans;
    }

    // 动态规划优化
    public int maxProduct3(int[] nums) {
        int ans = nums[0];
        int dpMax = nums[0];
        int dpMin = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int preMax = dpMax;   // 存储之前的dpMax
            dpMax = Math.max(nums[i],Math.max(nums[i] * dpMax,nums[i] * dpMin));
            dpMin = Math.min(nums[i],Math.min(nums[i] * preMax,nums[i] * dpMin));
            ans = Math.max(ans,dpMax);
        }
        return ans;
    }

    // 解法三： 本质算法 ———— 未算
}
