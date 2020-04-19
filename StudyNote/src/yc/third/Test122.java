package yc.third;

import java.util.HashMap;

public class Test122 {

    // 贪心算法
    public int maxProfit(int[] prices) {
        int profis = 0;
        int buy = 0;
        int sell = 1;
        for (; sell < prices.length; sell++) {
            if(prices[sell] < prices[sell - 1]){
                if(sell != 1){
                    profis += prices[sell - 1] - prices[buy];
                }
                buy = sell;
            }else if(sell == prices.length - 1){
                profis += prices[sell] - prices[buy];
            }
        }
        return profis;
    }

    // 贪心算法二： 将数组改造成盈利数组
    public int maxProfit2(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            int num = prices[i] - prices[i - 1];
            if(num > 0){
                profit += num;
            }
        }
        return profit;
    }
}
