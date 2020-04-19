package my_code_sum_up.code_dp;

import java.util.HashMap;

// 63. 不同路径 II
// 方法一：暴力递归
// 方法二：动态规划
public class Test63 {

    /**
     * 方法一：暴力递归(使用memo技术优化)
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        HashMap<String,Integer> memo = new HashMap<>();
        return going(0,0,row - 1,col - 1,obstacleGrid,memo);
    }

    private int going(int r, int c, int m, int n,int[][] obstacleGrid,HashMap<String,Integer> memo) {
        if(obstacleGrid[r][c] == 1 || obstacleGrid[m][n] == 1){
            return 0;
        }
        if(r == m && c == n){
            return 1;
        }
        String key = r + "@" + c;
        if(memo.containsKey(key)){
            return memo.get(key);
        }
        int ans = 0;
        if(r < m){
            ans += going(r+1,c,m,n,obstacleGrid,memo);
        }
        if(c < n){
            ans += going(r,c+1,m,n,obstacleGrid,memo);
        }
        memo.put(key,ans);
        return ans;
    }


    /**
     * 方法二：动态规划
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
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

    // 动态规划优化
    public int uniquePathsWithObstacles3(int[][] obstacleGrid) {
        //1. 获取网格的大小
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        //2. 创建存储对象的数组dp[i][j]: 表示从起点走到i，j这个点右多少种路径走法
        int[] dp = new int[n];
        //3. 遍历数组
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(obstacleGrid[i][j] == 1){
                    dp[j] = 0;  // 遇到了障碍物，则次数置0
                }else{
                    // 进入else表示此处没有障碍物
                    if(i == 0 && j == 0){
                        dp[j] = 1;        // 如果是开始的位置，则默认为1
                    }else if(i == 0){
                        dp[j] = dp[j - 1] == 0 ? 0 : 1;     // 如果是第一行非头位置，当前能不能走取决于前面的位置能不能走
                    }else if(j > 0){
                        dp[j] = dp[j] + dp[j  - 1];         // 如果非第一列，则走法为上一次和前一位的和
                    }
                    // 0列的值取决于当前列是否有障碍物，一旦有障碍物，全部为0
                }
            }
        }
        return dp[n - 1];
    }
}
