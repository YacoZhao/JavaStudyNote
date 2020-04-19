package yc.first;

//旋转图像
public class Test48 {
    /**
     * 方法一： 先将数组转置，然后按行反转
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        //1. 转置
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix.length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        //2.反转
        int n = matrix[0].length;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }

    /**
     * 方法二：旋转矩形的四个顶点
     * @param matrix
     */
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2 + n % 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                int[] cur = new int[4];
                int row = i;
                int col = j;
                for (int k = 0; k < 4; k++) {
                    cur[k] = matrix[row][col];
                    int temp = row;
                    row = col;
                    col = n - 1 - temp;
                }
                //列表添加了之后，进行旋转操作
                for (int k = 0; k < 4; k++) {
                    matrix[row][col] = cur[(k + 3) % 4];
                    int temp = row;
                    row = col;
                    col = n - 1 - temp;
                }
            }
        }
    }
}
