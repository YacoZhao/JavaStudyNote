package yc.second;

//最大子序和
public class Test53 {
    //正数增益数字
    public static int maxSubArray(int[] nums){
        //1. ans表示结果，初始化为数组第一个元素
        int ans = nums[0];
        //2. sum表示当前的累加值
        int sum = 0;
        //3. 循环遍历，如果当前的累加值为整数，那么表示当前的累加和还有能力向前挺进，如果下一步
        //为整数，则sum增加，如果下一步为负数，下一步减小，最后取ans和sum的最大值，显然
        //如果加了，则前取到了sum，如果没加，依然保留之前的sum
        //注意： 这里考虑全是负数的情况，因为Math函数的存在，最终也可以返回数组中最大的单个元素
        for (int num : nums) {
            if (sum < 0) {
                sum = num;
            } else {
                sum += num;
            }
            ans = Math.max(ans, sum);
        }
        //4. 返回结果
        return ans;
    }

    // 暴力穷举
    public static int maxSubArray1(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int res = 0;
            for (int j = 0; i + j < nums.length ; j++) {
                res += nums[i + j];
                max = Math.max(max,res);
            }
        }
        return max;
    }

    // 改递归实现
    public static int maxSubArray2(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int[][] dp = new int[nums.length][nums.length];
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                dp[i][j] = j == i ? nums[i] : dp[i][j - 1] + nums[j];
                res = Math.max(res,dp[i][j]);
            }
        }
        return res;
    }

    // 优化动态规划，二位数组改造为一位数组
    public static int maxSubArray3(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int[] dp = new int[nums.length];
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                dp[j] = i == j ? nums[i] : dp[j - 1] + nums[j];
                res = dp[j] > res ? dp[j] : res;
            }
        }
        return res;
    }

    // 解法四： 动态规划求解
    public static int maxSubArray4(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int curMax = nums[0];
        int sumMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curMax = Math.max(curMax + nums[i],nums[i]);
            sumMax = Math.max(curMax,sumMax);
        }
        return sumMax;
    }

    public static void main(String[] args) {
        int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray3(arr));
    }
}
