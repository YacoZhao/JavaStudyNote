package yc.second;

public class Test70 {
    /**
     * 动态规划求解爬楼梯问题
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int dp[] = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
