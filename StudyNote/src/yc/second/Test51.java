package yc.second;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//N皇后问

/**
 * 此题最关键的地方在于弄明白first[] 和 second[] 两个用于表示当前位置是否被攻击的数组含义要弄清楚
 */
public class Test51 {

    List<List<String>> res = new ArrayList<>();

    int[] rows;  //用来表示此位置的哪一列可以被皇后攻击，如果为1表示此列已经有皇后了，默认为0
    int[] first; //用来表示此位置的主对角线上是否有皇后，可以看出来，主对角线上的元素row-col为一个固定的常数，但是可能小于0；
    int[] second;//用来表示次对角线上是否有皇后，从对角线的性质是行列相加为一个固定的常熟
    int n; // 表示皇后数
    int[] queues;  //用于存放皇后

    /**
     * 主方法进口
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        rows = new int[n];
        queues = new int[n];
        first = new int[2 * n - 1];
        second = new int[2 * n - 1];
        this.n = n;
        backtrack(0);
        return res;
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
                    addSolution();
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
     * 将当前结果添加到结果集中
     */
    private void addSolution() {
        List<String> ans = new ArrayList<>();
        for (int queue : queues) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < queue; i++) {
                builder.append(".");
            }
            builder.append("Q");
            for (int i = 0; i < n - queue - 1; i++) {
                builder.append(".");
            }
            ans.add(builder.toString());
        }
        res.add(ans);
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
