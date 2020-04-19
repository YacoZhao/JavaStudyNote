package yc.al.zuo.advanced;

// 7-2 最长增长子序列
public class Class7_Code2_LongestIncreaseSubSequence {

    // 方法一： 动态规划求出每个位置的最长递增子序列的长度
    public static int[] longestIncreaseSubSequence(int[] arr){
        if(arr == null || arr.length == 0) {
            return null;
        }
        int[] dp = new int[arr.length];
        int resIndex = 0;
        int arrIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if(arr[i] > arr[j]){
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
            }
            if(dp[i] > resIndex){
                resIndex = dp[i];
                arrIndex = i;
            }
        }
        // 根据dp数组，找最大的子序列
        int[] res = new int[resIndex];
        res[--resIndex] = arr[arrIndex--];
        for (int j = arrIndex; j >= 0; j--) {
            if(arr[j] < res[resIndex]){
                res[--resIndex] = arr[j];
            }
        }
        return res;
    }

    // 方法二： NlogN解法————待补充（没看懂）

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = { 2, 1, 5, 3, 6, 4, 8, 9, 7 };
        printArray(arr);
        printArray(longestIncreaseSubSequence(arr));
        //printArray(lis2(arr));

    }
}
