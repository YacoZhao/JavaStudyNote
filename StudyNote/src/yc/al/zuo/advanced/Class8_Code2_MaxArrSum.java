package yc.al.zuo.advanced;

// 8-2  子数组的最大累加和
public class Class8_Code2_MaxArrSum {

    // 主函数入口
    public static int maxSum(int[] arr) {
        int res = arr[0];
        int dp[] = new int[arr.length];
        dp[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            dp[i] = Math.max(arr[i], dp[i - 1] + arr[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // 解法二
    public static int maxSum2(int[] arr){
        int res = arr[0];
        int dp = arr[0];
        for (int i = 1; i < arr.length; i++) {
            dp = Math.max(arr[i], dp + arr[i]);
            res = Math.max(res, dp);
        }
        return res;
    }

    // 测试函数
    public static void main(String[] args) {
        int[] arr = new int[]{1,-2,3,5,-2,6,-1};
        System.out.println("方法一：" + maxSum(arr));
        System.out.println("方法二：" + maxSum2(arr));
    }

}
