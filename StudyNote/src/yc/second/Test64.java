package yc.second;

public class Test64 {
    /**
     * 动态规划
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        //1. 获取网格的大小
        int m = grid.length;
        int n = grid[0].length;
        //2. 创建存储结果的数组dp[i][j] 表示从起点到ij上面路径数总和最小
        int[][] dp = new int[m][n];
        //3. 初始化数组
        int sum = 0;
        for (int i = 0; i < m; i++) {
            sum += grid[i][0];
            dp[i][0] = sum;
        }
        sum = 0;
        for (int j = 0; j < n; j++) {
            sum += grid[0][j];
            dp[0][j] = sum;
        }
        //4. 循环遍历数组
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        //5. 返回结果
        return dp[m - 1][n - 1];
    }
}
