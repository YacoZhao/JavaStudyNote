package yc.second;

import java.util.HashSet;
import java.util.Set;

public class Test73 {
    /**
     * 解法一： 先用两个集合记录0出现的行和列，然后遍历数组，将相应的行和列至0
     * @param matrix
     */
    public void setZeroes1(int[][] matrix) {
        int R = matrix.length;  //行数
        int C = matrix[0].length; //列数
        Set<Integer> row = new HashSet<>();  //存放行数
        Set<Integer> col = new HashSet<>();  //存放列数
        //遍历数组
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (matrix[i][j] == 0) {
                    row.add(i);
                    col.add(j);
                }
            }
        }
        //将相应的行和列至0
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (row.contains(i) || col.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
