package yc.second;

public class Test72 {
    /**
     * 动态规划求解编辑距离问题
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();  //计算出word1字符串的长度
        int n = word2.length();  //计算出word2字符串的长度
        //创建动态规划数组  —— dp[i][j]表示word1的前i个字符串转化为word2的前j个字符串所使用的最少操作数
        int[][] dp = new int[m + 1][n + 1];
        //初始化数组
        for (int i = 1; i < n + 1; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i < m + 1; i++) {
            dp[i][0] = i;
        }
        //循环遍历数组
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
            }
        }
        return dp[m][n];
    }
}
