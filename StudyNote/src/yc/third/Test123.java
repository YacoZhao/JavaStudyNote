package yc.third;

//123. 买卖股票的最佳时机 III
public class Test123 {
    // 二分自己撸
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

    // 解法二： 动态规划
    public int maxProfit2(int[] prices) {
        if(prices.length == 0) return 0;
        // 创建dp数组： 表示前i天交易j次的最大收益
        int[][] dp = new int[prices.length][3];
        // 遍历数组填数
        for (int i = 1; i < 3; i++) {
            for (int j = 1; j < prices.length; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 0; k <= j; k++) {
                    min = Math.min(min,prices[k] - dp[k][i - 1]);
                }
                dp[j][i] = Math.max(dp[j - 1][i],prices[j] - min);
            }
        }
        return dp[prices.length - 1][2];
    }

    // 解法二： 动态规划---（优化一，不用每次计算min,没遍历依次，改变一下min）
    public int maxProfit3(int[] prices) {
        if(prices.length == 0) return 0;
        // 创建dp数组： 表示前i天交易j次的最大收益
        int[][] dp = new int[prices.length][3];
        // 遍历数组填数
        for (int i = 1; i < 3; i++) {
            int min = prices[0];
            for (int j = 1; j < prices.length; j++) {
                min = Math.min(min,prices[j] - dp[j][i - 1]);
                dp[j][i] = Math.max(dp[j - 1][i],prices[j] - min);
            }
        }
        return dp[prices.length - 1][2];
    }

    // 解法二：  动态规划---（使用降维dp数组进行优化）（改造数组）
    public int maxProfit4(int[] prices) {
        if(prices.length == 0) return 0;
        // 创建dp数组： 表示前i天交易j次的最大收益
        int[][] dp = new int[3][prices.length];
        // 遍历数组填数
        for (int i = 1; i < 3; i++) {
            int min = prices[0];
            for (int j = 1; j < prices.length; j++) {
                min = Math.min(min,prices[j] - dp[i - 1][j]);
                dp[i][j] = Math.max(dp[i][j - 1],prices[j] - min);
            }
        }
        return dp[2][prices.length - 1];
    }

    // 解法二：  动态规划---（使用降维dp数组进行优化）（改造数组）
    public int maxProfit5(int[] prices) {
        if(prices.length == 0) return 0;
        // 创建dp数组： 表示前i天交易j次的最大收益
        int[] dp = new int[prices.length];
        // 遍历数组填数
        for (int i = 1; i < 3; i++) {
            int min = prices[0];
            for (int j = 1; j < prices.length; j++) {
                min = Math.min(min,prices[j] - dp[j]);
                dp[j] = Math.max(dp[j - 1],prices[j] - min);
            }
        }
        return dp[prices.length - 1];
    }
}
