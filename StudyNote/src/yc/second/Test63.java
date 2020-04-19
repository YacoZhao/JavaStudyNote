package yc.second;

public class Test63 {
    /**
     * 动态规划
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //1. 获取网格的大小
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        //2. 创建存储对象的数组dp[i][j]: 表示从起点走到i，j这个点右多少种路径走法
        int[][] dp = new int[m][n];
        //3. 初始化路径
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            } else {
                dp[i][0] = 1;
            }
        }
        for (int j = 0; j < n; j++) {
            if (obstacleGrid[0][j] == 1) {
                break;
            } else {
                dp[0][j] = 1;
            }
        }
        //4. 遍历数组
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] != 1) {  //只有当前当个可以走时，才会赋值，否则，默认为空
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        //5. 返回结果
        return dp[m - 1][n - 1];
    }
}
