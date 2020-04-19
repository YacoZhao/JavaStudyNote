package yc.al.zuo.advanced;

// 6-1 换钱的方法数
public class Class6_Code1_CoinsWay {

    // 方法一： 暴力递归求解
    public static int coins1(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim < 0) return 0;
        return getAns(arr, 0, aim);
    }

    private static int getAns(int[] arr, int index, int aim) {
        int res = 0;
        if(index == arr.length){
            return aim == 0 ? 1 : 0;
        }else{
            for (int i = 0; aim - (arr[index] * i) >= 0; i++) {
                res += getAns(arr,index+1,aim - (arr[index] * i));
            }
        }
        return res;
    }

    // 方法二：动态规划
    public static int coinst2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length][aim + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; arr[0] * j <= aim; j++) {
            dp[0][arr[0] * j] = 1;
        }
        int num = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                num = 0;
                for (int k = 0; j - arr[i] * k >= 0; k++) {
                    num += dp[i - 1][j - arr[i] * k];
                }
                dp[i][j] = num;
            }
        }
        return dp[arr.length - 1][aim];
    }

    // 方法三：二维数组降维
    public static int coinst3(int[] arr, int aim){
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[] dp = new int[aim + 1];
        for (int j = 0; arr[0] * j <= aim; j++) {
            dp[arr[0] * j] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                dp[j] += j - arr[i] >= 0 ? dp[j - arr[i]] : 0;
            }
        }
        return dp[aim];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5,10,25,1,2,20,50};
        System.out.println(coins1(arr,150));
        System.out.println(coinst2(arr,150));
        System.out.println(coinst3(arr,150));
    }
}
