package my_code_sum_up.code_dp;

//123. 买卖股票的最佳时机 III
// 方法一： 分治
// 方法二： 动态规划
public class Test123 {


    /**
     * 方法一： 分治
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        int end = prices.length;
        int ans = getAns(0,end - 1,prices);
        for (int i = 1; i < end - 1; i++) {
            ans = Math.max(ans,getAns(0,i,prices) + getAns(i,end - 1,prices));
        }
        return ans;
    }

    private int getAns(int s, int e, int[] prices) {
        int ans = 0;
        int dp = 0;
        for (int i = s + 1; i <= e; i++) {
            int num = prices[i] - prices[i - 1];
            dp = Math.max(dp + num, num);
            ans = Math.max(dp,ans);
        }
        return ans;
    }

    /**
     * 方法二：动态规划
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        if(prices.length < 2) return 0;
        int[][] dp = new int[prices.length][3];
        // dp[i][j]表示，第天交易j次的最大收益值，则返回值应该为dp[prices.length - 1][2]
        for (int i = 1; i < 3; i++) {
            // 从交易一次开始遍历数组
            for (int j = 1; j < prices.length; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < j; k++) {
                    min = Math.min(min,prices[k] - dp[k][i - 1]);
                }
                dp[j][i] = Math.max(dp[j-1][i],prices[j] - min);
            }
        }
        return dp[prices.length  - 1][2];
    }

    // 优化动态规划(每次遍历的时候记录min值)
    public int maxProfit3(int[] prices) {
        if(prices.length < 2) return 0;
        int[][] dp = new int[3][prices.length];
        // dp[i][j]表示，第天交易j次的最大收益值，则返回值应该为dp[prices.length - 1][2]
        for (int i = 1; i < 3; i++) {
            int min = prices[0];
            // 从交易一次开始遍历数组
            for (int j = 1; j < prices.length; j++) {
                min = Math.min(min,prices[j] - dp[i - 1][j]);
                dp[i][j] = Math.max(dp[i][j - 1],prices[j] - min);
            }
        }
        return dp[2][prices.length  - 1];
    }

    // 优化动态规划数组降维
    public int maxProfit4(int[] prices) {
        if(prices.length < 2) return 0;
        int[] dp = new int[prices.length];
        // dp[i][j]表示，第天交易j次的最大收益值，则返回值应该为dp[prices.length - 1][2]
        for (int i = 1; i < 3; i++) {
            int min = prices[0];
            // 从交易一次开始遍历数组
            for (int j = 1; j < prices.length; j++) {
                min = Math.min(min,prices[j] - dp[j]);
                dp[j] = Math.max(dp[j - 1],prices[j] - min);
            }
        }
        return dp[prices.length  - 1];
    }
}
