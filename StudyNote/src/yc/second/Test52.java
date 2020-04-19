package yc.second;

import java.util.ArrayList;
import java.util.List;

//N皇后问题Ⅱ
public class Test52 {

    int[] rows;  //用来表示此位置的哪一列可以被皇后攻击，如果为1表示此列已经有皇后了，默认为0
    int[] first; //用来表示此位置的主对角线上是否有皇后，可以看出来，主对角线上的元素row-col为一个固定的常数，但是可能小于0；
    int[] second;//用来表示次对角线上是否有皇后，从对角线的性质是行列相加为一个固定的常熟
    int n; // 表示皇后数
    int[] queues;  //用于存放皇后
    int count = 0;  //用于存放计数器

    /**
     * 主方法进口
     * @param n
     * @return
     */
    public int totalNQueens(int n) {
        rows = new int[n];
        queues = new int[n];
        first = new int[2 * n - 1];
        second = new int[2 * n - 1];
        this.n = n;
        backtrack(0);
        return count;
    }

    /**
     * 回溯主体
     * @param row
     */
    private void backtrack(int row) {
        if (row >= n) return;
        for (int col = 0; col < n; col++) {
            if (isPlaceQueue(row, col)) {
                //在当前位置放置皇后
                placeQueue(row, col);
                //如果row已经是最后一个位置，则添加元素
                if (row == n - 1) {
                    count++;
                }
                //回溯
                backtrack(row + 1);
                //撤销添加元素操作
                removeQueue(row, col);
            }
        }
    }

    /**
     * 回溯，溢出当前位置的皇后
     * @param row
     * @param col
     */
    private void removeQueue(int row, int col) {
        queues[row] = 0;  //撤销当前皇后
        //撤销当前所有标志位
        rows[col] = 0;
        first[row - col + n - 1] = 0;
        second[row + col] = 0;
    }

    /**
     * 放皇后
     * @param row
     * @param col
     */
    private void placeQueue(int row, int col) {
        queues[row] = col;
        rows[col] = 1;
        first[row - col + n - 1] = 1;
        second[row + col] = 1;
    }

    /**
     * 判断当前位置是否可以方式皇后
     *
     * @return
     */
    private boolean isPlaceQueue(int row, int col) {
        int res = rows[col] + first[row - col + n - 1] + second[row + col];
        return res == 0;
    }
}
