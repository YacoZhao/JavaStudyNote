package yc.al.zuo.base;

import java.util.Arrays;

/**
 * 旋转打印矩阵
 * @author code_yc
 * @version 1.0
 * @date 2020/4/24 10:47
 */
public class Class3_Code5_PrintMatrixSpiralOrder {

    // 函数主体
    public static void spiralOrderPrint(int[][] matrix) {
        if(matrix == null) return;
        int row = matrix.length;    // 行数
        int col = matrix[0].length; // 列数
        int start_row = 0, start_col = 0;  // 起始行列
        int end_row = row - 1, end_col = col - 1;  // 终止行列
        while(start_row <= end_row && start_col <= end_col) {
            printMatrix(matrix,start_row++,start_col++,end_row--,end_col--);
        }
    }

    private static void printMatrix(int[][] matrix, int tR, int tC, int dR, int dC) {
        if(tR == dR) {
            // 只有一行，那么只要将所有的列打印出来即可
            for (int i = tC; i <= dC; i++) {
                System.out.println(matrix[tR][i] + " ");
            }
        }else if(tC == dC) {
            // 如果只有一列的话，那么只要所有的行打印出来即可
            for (int i = tR; i <= dR; i++) {
                System.out.println(matrix[i][tC] + " ");
            }
        }else{
            int curC = tC;
            int curR = tR;

            // 旋转打印
            while(curC != dC){
                System.out.println(matrix[tR][curC++] + " ");
            }
            while(curR != dR) {
                System.out.println(matrix[curR++][dC] + " ");
            }
            while(curC != tC) {
                System.out.println(matrix[dR][curC--] + " ");
            }
            while(curR != tR){
                System.out.println(matrix[curR--][tC] + " ");
            }
        }
    }

    // 测试函数
    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
        System.out.println("原始数组为：");
        for (int[] arrs : matrix) {
            System.out.println(Arrays.toString(arrs));
        }
        System.out.println("------------------------------------");
        System.out.println("旋转打印为：");
        spiralOrderPrint(matrix);
    }
}
