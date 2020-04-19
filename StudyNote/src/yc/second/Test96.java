package yc.second;

public class Test96 {
    /**
     * 动态规划
     * dp[i] 表示从1到i所组成的所有组构成的二叉搜索树的所有可能的结果
     * 遍历数组1到i
     * 到根节点为j时，j再1到i之间，组成的可能性为左边的所有可能性与右边所有可能性的乘积
     * 遍历相加则可以了
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}
