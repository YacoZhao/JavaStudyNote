package yc.third;

public class Test121 {

    // 解法一：  暴力穷举
    public int maxProfit(int[] prices) {
        int ans = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                ans = Math.max(ans, prices[j] - prices[i]);
            }
        }
        return ans;
    }

    // 解法二： 双指针算法
    public int maxProfit2(int[] prices){
        int buy = 0;
        int sell = 0;
        int ans = 0;
        for (; sell < prices.length;) {
            if(prices[sell] < prices[buy]){
                buy = sell;
            }
            ans = Math.max(prices[sell++] - prices[buy],ans);
        }
        return ans;
    }

    // 解法三： 嵌套53题最大子序和，考虑使用进一步优化算法
    public int maxProfit3(int[] prices) {
        int ans = 0;
        int dp = 0;
        for (int i = 1; i < prices.length; i++) {
            int num = prices[i] - prices[i - 1];
            dp = Math.max(dp + num, num);
            ans = Math.max(dp,ans);
        }
        return ans;
    }


}
