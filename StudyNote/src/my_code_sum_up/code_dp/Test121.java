package my_code_sum_up.code_dp;

// 买卖股票的最佳时机
// 方法一： 暴力枚举
// 方法二： 双指针
// 方法三： 构造出每两天之间的价格波动数组，找出最大子序和
public class Test121 {

    /**
     * 方法一： 暴力枚举
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        if(prices.length < 2) return 0;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length  - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                ans = Math.max(ans,prices[j] - prices[i]);
            }
        }
        return ans;
    }

    /**
     * 方法二： 双指针算法（若当前的sell指针价格低于当前的buy指针的价格，则将buy指针移动到当前的sell位置）
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        if(prices.length < 2) return 0;
        int ans = 0;
        int buy = 0;
        int sell = 1;
        int len = prices.length;
        while(sell < len) {
            if(prices[sell] > prices[buy]){
                ans = Math.max(prices[sell] - prices[buy],ans);
                sell++;
            }else{
                buy = sell;
                sell++;
            }
        }
        return ans;
    }

    /**
     * 方法三： 构造价格波动数组，求最大子序和
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        int ans = 0;
        int curMax = 0;
        for (int i = 1; i < prices.length; i++) {
            int cur = prices[i] - prices[i-1];
            curMax = Math.max(cur,curMax + cur);
            ans = Math.max(ans,curMax);
        }
        return ans;
    }
}
