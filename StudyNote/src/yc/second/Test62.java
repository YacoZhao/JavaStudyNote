package yc.second;

public class Test62 {
    /**
     * 动态规划求解
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        //1. 边界条件判断
        if (m == 1 || n == 1) {
            return 1;
        }
        //2. 创建存储对象的数组dp[i][j]: 表示从起点走到i，j这个点右多少种路径走法
        int[][] dp = new int[m][n];
        //3. 初始化路径
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = 1;
        }
        //4. 遍历数组
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        //5. 返回结果
        return dp[m - 1][n - 1];
    }
}
