package yc.third;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//120. 三角形最小路径和
public class Test120 {

    // 解法一： 暴力递归
    public int minimumTotal(List<List<Integer>> triangle) {
        return getAns(triangle, 0, 0);
    }

    // row表示当前行————col表示当前列
    private int getAns(List<List<Integer>> triangle, int row, int col) {
        if (row == triangle.size()) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        List<Integer> cur = triangle.get(row);
        ans = Math.min(ans, cur.get(col) + getAns(triangle, row + 1, col));
        if (col + 1 < cur.size()) {
            ans = Math.min(ans, cur.get(col + 1) + getAns(triangle, row + 1, col + 1));
        }
        return ans;
    }

    // 解法一： 使用memo技术优化
    public int minimumTotal2(List<List<Integer>> triangle) {
        HashMap<String, Integer> memo = new HashMap<String, Integer>();
        return getAns2(triangle, 0, 0, memo);
    }

    private int getAns2(List<List<Integer>> triangle, int row, int col, HashMap<String, Integer> memo) {
        if (row == triangle.size()) {
            return 0;
        }
        String key = row + "$" + col;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int ans = Integer.MAX_VALUE;
        List<Integer> cur = triangle.get(row);
        ans = Math.min(ans, cur.get(col) + getAns2(triangle, row + 1, col, memo));
        if (col + 1 < cur.size()) {
            ans = Math.min(ans, cur.get(col + 1) + getAns2(triangle, row + 1, col + 1, memo));
        }
        memo.put(key, ans);
        return ans;
    }

    // 解法二：  暴力递归改动态规划
    public int minimumTotal3(List<List<Integer>> triangle) {
        int row = triangle.size();
        int[][] dp = new int[row][triangle.get(row - 1).size()];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < row; i++) {
            List<Integer> cur = triangle.get(i);
            int size = cur.size();
            dp[i][0] = dp[i - 1][0] + cur.get(0);
            for (int j = 1; j < size - 1; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + cur.get(j);
            }
            dp[i][size - 1] = dp[i - 1][size - 2] + cur.get(size - 1);
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < dp[0].length; i++) {
            res = Math.min(res, dp[row - 1][i]);
        }
        return res;
    }

    // 解法二： 优化动态规划(二维数组转成一维数组)
    public int minimumTotal4(List<List<Integer>> triangle) {
        int row = triangle.size();
        int[] dp = new int[triangle.get(row - 1).size()];
        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < row; i++) {
            List<Integer> cur = triangle.get(i);
            int size = cur.size();
            dp[size - 1] = dp[size - 2] + cur.get(size - 1);
            for (int j = size - 2; j >= 1; j--) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + cur.get(j);
            }
            dp[0] = dp[0] + cur.get(0);
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < dp.length; i++) {
            res = Math.min(res, dp[i]);
        }
        return res;
    }
}
