package yc.second;

import java.util.ArrayList;
import java.util.List;

public class Test54 {
    /**
     * 方法一： 按要求的路径遍历数组，碰到故障就转变方向
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        //1. 申明返回结果
        List<Integer> res = new ArrayList<>();
        //2. 特殊情况声明
        if (matrix.length == 0) {
            return res;
        }
        //3. 申明使用的变量
        int row = matrix.length;   // 数组的行数
        int col = matrix[0].length; // 数组的列数
        boolean[][] use = new boolean[row][col]; //表示数组中单元的使用情况，默认未使用为false
        int[] dr = {0, 1, 0, -1};      //声明行的移动情况，先不动，再向下，再向上
        int[] dc = {1, 0, -1, 0};      //声明列的移动情况，先不动，再向右，再向左
        int x = 0, y = 0;           //表示数组的第i行第j列
        int di = 0;                 //行列移动方向的索引
        //4. 循环遍历二维数组的每一个格子
        for (int i = 0; i < row * col; i++) {
            //4.1 首先先将当前的元素放入结果集中
            res.add(matrix[x][y]);
            //4.2 标志位置为true，表示此位置已经访问过了
            use[x][y] = true;
            //4.3 更新行和列，cr和cc表示按照当前的路线前进的结果
            int cr = x + dr[di];
            int cc = y + dc[di];
            //4.4 判断当前前进的结果是否越界，或者走到了已经遍历过的点，如果没有按新的行列添加进结果集
            if (cr >= 0 && cr < row && cc >= 0 && cc < col && !use[cr][cc]) {
                x = cr;
                y = cc;
            } else {
                //4.5 越界或者碰到已近遍历的元素，按行列依次变换的顺序，调整方向
                di = (di + 1) % 4;
                x = x + dr[di];
                y = y + dc[di];
            }
        }
        //5. 返回结果
        return res;
    }

    /**
     * 方法二： 按层进行模拟，先遍历出最外层的元素，然后到次层，最后到最里面
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder2(int[][] matrix){
        //1. 首先声明结果
        List<Integer> res = new ArrayList<>();
        //2. 特殊情况判别
        if (matrix.length == 0) {
            return res;
        }
        //3. 申明所需要的变量
        int row = matrix.length;   //行数
        int col = matrix[0].length; //列数
        int r1 = 0, c1 = 0;        //声明起始行列
        int r2 = row - 1, c2 = col - 1; //声明终止行列
        //4. 循环遍历
        while (r1 <= r2 && c1 <= c2) {
            //4.1 首先从起始列开始，向右遍历出数据加入结果集，到终止类结束，如果只有一列，加入了第一个元素就结束了
            for (int c = c1; c <= c2; c++) {
                res.add(matrix[r1][c]);
            }
            //4.2 再从下一行开始，从起始行的下一列遍历到终止行，如果只有一行的话，这个条件不会执行
            for (int r = r1 + 1; r <= r2; r++) {
                res.add(matrix[r][c2]);
            }
            //4.3 如果只有一行或者一列，那么到这里已近全部遍历完了，不用继续往下执行
            if (r1 < r2 && c1 < c2) {
                //4.4 从终止行的终止列的下一列往回遍历
                for (int c = c2 - 1; c >= 0; c--) {
                    res.add(matrix[r2][c]);
                }
                //4.5 从终止行起始列的上一列在往回执行
                for (int r = r2 - 1; r > 0; r--) {
                    res.add(matrix[r][c1]);
                }
            }
            //4.6 调整行列指针， 往内部缩一位
            r1++;
            c1++;
            r2--;
            c2--;
        }
        return res;
    }
}
