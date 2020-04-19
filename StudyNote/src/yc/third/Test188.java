package yc.third;

// 188. 买卖股票的最佳时机 IV
public class Test188 {

    // 修改之前的123题，k从2次变成k次
    public int maxProfit(int k, int[] prices) {
        if(prices.length == 0) return 0;
        k = k > prices.length - 1 ? prices.length - 1 : k;  // 交易次数不可能大雨股票之间的间隔总数
        // 创建dp数组： 表示前i天交易j次的最大收益
        int[] dp = new int[prices.length];
        // 遍历数组填数
        for (int i = 0; i < k; i++) {
            int min = prices[0];
            for (int j = 1; j < prices.length; j++) {
                min = Math.min(min,prices[j] - dp[j]);
                dp[j] = Math.max(dp[j - 1],prices[j] - min);
            }
        }
        return dp[prices.length - 1];
    }
}
