package yc.al.zuo.advanced;

// 7-1 矩阵的最小路径和
public class Class7_Code1_MinPathSum {

    // 方法一： 暴力递归
    public static int minPathSum1(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int row = matrix.length;
        int col = matrix[0].length;
        return getAns(matrix, 0, 0, row - 1, col - 1);
    }

    private static int getAns(int[][] matrix, int r, int c, int row, int col) {
        if (r == row && c == col) {
            return matrix[r][c];
        }
        if (r == row) {
            return matrix[r][c] + getAns(matrix, r, c + 1, row, col);
        }
        if (c == col) {
            return matrix[r][c] + getAns(matrix, r + 1, c, row, col);
        }
        return matrix[r][c] + Math.min(getAns(matrix, r + 1, c, row, col), getAns(matrix, r, c + 1, row, col));
    }

    // 方法二： 动态规划
    public static int minPathSum2(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = matrix[i][j];
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + matrix[i][j];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + matrix[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
                }
            }
        }
        return dp[row - 1][col - 1];
    }

    // 方法三： 动态规划优化
    public static int minPathSum3(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int[] dp = new int[col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) {
                    dp[j] = matrix[i][j];
                } else if (i == 0) {
                    dp[j] = dp[j - 1] + matrix[i][j];
                } else if (j == 0) {
                    dp[j] = dp[j] + matrix[i][j];
                } else {
                    dp[j] = Math.min(dp[j], dp[j - 1]) + matrix[i][j];
                }
            }
        }
        return dp[col - 1];
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // ---------------------------测试集------------------------------
    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // for test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 10);
            }
        }
        return result;
    }

    // for test
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // int[][] m = generateRandomMatrix(3, 4);
        int[][] m = { { 1, 3, 5, 9 }, { 8, 1, 3, 4 }, { 5, 0, 6, 1 },
                { 8, 8, 4, 0 } };
        printMatrix(m);
        System.out.println(minPathSum1(m));
        System.out.println(minPathSum2(m));
        System.out.println(minPathSum3(m));
    }
}
