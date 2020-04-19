package my_code_sum_up.code_dp;

import java.util.HashMap;

//64. 最小路径和
// 方法一： 暴力递归
// 方法二： 动态规划
public class Test64 {

    /**
     * 方法一： 暴力递归(使用memo技术优化)
     * @param grid
     * @return
     */
    public int minPathSum1(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        HashMap<String,Integer> memo = new HashMap<>();
        return process(grid,0,0,r - 1,c - 1,memo);
    }

    private int process(int[][] grid, int r, int c,int m,int n,HashMap<String,Integer> memo) {
        if(r == m && c == n){
            return grid[m][n];
        }
        String key = r + "@" + c;
        if(memo.containsKey(key)){
            return memo.get(key);
        }
        int ans = grid[r][c];
        if(r == m) {
            ans += process(grid,r,c+1,m,n,memo);
        }else if(c == n){
            ans += process(grid,r+1,c,m,n,memo);
        }else{
            ans += Math.min(process(grid,r,c+1,m,n,memo),process(grid,r+1,c,m,n,memo));
        }
        memo.put(key,ans);
        return ans;
    }

    /**
     * 动态规划
     * @param grid
     * @return
     */
    public int minPathSum2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(i == 0 && j == 0){
                    dp[i][j] = grid[i][j];
                }else if(i == 0){
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                }else if(j == 0){
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                }else{
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    // 优化动态规划
    public int minPathSum3(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(i == 0 && j == 0){
                    dp[j] = grid[i][j];
                }else if(i == 0){
                    dp[j] = dp[j - 1] + grid[i][j];
                }else if(j == 0){
                    dp[j] = dp[j] + grid[i][j];
                }else{
                    dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
                }
            }
        }
        return dp[n - 1];
    }
}
