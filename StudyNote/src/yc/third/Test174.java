package yc.third;

public class Test174 {
    class Solution {
        /**
         * 动态规划求解
         * 注意： 无论在任何地方，战士的血量都必须为整数，最小为1
         * @param dungeon
         * @return
         */
        public int calculateMinimumHP(int[][] dungeon) {
            int row = dungeon.length;
            int col = dungeon[0].length;
            int[][] dp = new int[row][col];
            for (int i = row - 1; i >= 0; i--) {
                for (int j = col - 1; j >= 0; j--) {
                    if (i == row - 1 && j == col - 1) {
                        dp[i][j] = dungeon[i][j] < 0 ? 0 - dungeon[i][j] + 1: 1;
                    } else if (i == row - 1) {
                        // 只能向右走
                        dp[i][j] = Math.max(dp[i][j + 1] - dungeon[i][j], 1);
                    } else if (j == col - 1) {
                        // 只能向下走
                        dp[i][j] = Math.max(dp[i + 1][j] - dungeon[i][j], 1);
                    } else {
                        // 可以向下或者向右走
                        dp[i][j] = Math.min(Math.max(dp[i][j + 1] - dungeon[i][j], 1), Math.max(dp[i + 1][j] - dungeon[i][j], 1));
                    }
                }
            }
            return dp[0][0];
        }

        // 优化动态规划
        public int calculateMinimumHP2(int[][] dungeon) {
            int row = dungeon.length;
            int col = dungeon[0].length;
            int[] dp = new int[col];
            for (int i = row - 1; i >= 0; i--) {
                for (int j = col - 1; j >= 0; j--) {
                    if (i == row - 1 && j == col - 1) {
                        dp[j] = dungeon[i][j] < 0 ? 0 - dungeon[i][j] + 1: 1;
                    } else if (i == row - 1) {
                        // 只能向右走
                        dp[j] = Math.max(dp[j + 1] - dungeon[i][j], 1);
                    } else if (j == col - 1) {
                        // 只能向下走
                        dp[j] = Math.max(dp[j] - dungeon[i][j], 1);
                    } else {
                        // 可以向下或者向右走
                        dp[j] = Math.min(Math.max(dp[j + 1] - dungeon[i][j], 1), Math.max(dp[j] - dungeon[i][j], 1));
                    }
                }
            }
            return dp[0];
        }
    }
}
