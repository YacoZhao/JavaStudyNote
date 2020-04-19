package my_code_sum_up.code_dp;

// 174. 地下城游戏
// 方法一： 暴力递归
// 方法二： 动态规划
// 方法三； 优化动态规划
public class Test174 {

    // 暴力递归整一把
    public int calculateMinimumHP1(int[][] dungeon) {
        int row = dungeon.length;
        int col = dungeon[0].length;
        return getRes(dungeon,0,0,row - 1,col - 1);
    }

    private int getRes(int[][] dungeon, int r, int c, int row, int col) {
        if(r == row && c == col) {
            return dungeon[r][c] < 0 ? 1 - dungeon[r][c] : 1;
        }
        if(r == row) {
            int next = getRes(dungeon, r, c + 1, row, col);
            return dungeon[r][c] < next ? next - dungeon[r][c] : 1;
        }
        if(c == col) {
            int next = getRes(dungeon, r + 1, c, row, col);
            return dungeon[r][c] < next ? next - dungeon[r][c] : 1;
        }
        int next = Math.min(getRes(dungeon, r, c + 1, row, col),getRes(dungeon, r + 1, c, row, col));
        return dungeon[r][c] < next ? next - dungeon[r][c] : 1;
    }

    // 2. 暴力递归改动态规划
    public int calculateMinimumHP2(int[][] dungeon) {
        int row = dungeon.length;
        int col = dungeon[0].length;
        int[][] dp = new int[row][col];
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                if(i == row - 1 && j == col - 1){
                    dp[i][j] =  dungeon[i][j] < 0 ? 1 - dungeon[i][j] : 1;
                }else if(i == row - 1){
                    dp[i][j] = dungeon[i][j] < dp[i][j+1] ? dp[i][j+1] - dungeon[i][j] : 1;
                }else if(j == col- 1){
                    dp[i][j] = dungeon[i][j] < dp[i+1][j] ? dp[i+1][j] - dungeon[i][j] : 1;
                }else{
                    int next = Math.min(dp[i+1][j],dp[i][j+1]);
                    dp[i][j] = dungeon[i][j] < next ? next - dungeon[i][j] : 1;
                }
            }
        }
        return dp[0][0];
    }

    // 3. 动态规划优化
    public int calculateMinimumHP3(int[][] dungeon) {
        int row = dungeon.length;
        int col = dungeon[0].length;
        int[] dp = new int[col];
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                if(i == row - 1 && j == col - 1){
                    dp[j] =  dungeon[i][j] < 0 ? 1 - dungeon[i][j] : 1;
                }else if(i == row - 1){
                    dp[j] = dungeon[i][j] < dp[j+1] ? dp[j+1] - dungeon[i][j] : 1;
                }else if(j == col- 1){
                    dp[j] = dungeon[i][j] < dp[j] ? dp[j] - dungeon[i][j] : 1;
                }else{
                    int next = Math.min(dp[j],dp[j+1]);
                    dp[j] = dungeon[i][j] < next ? next - dungeon[i][j] : 1;
                }
            }
        }
        return dp[0];
    }

}
