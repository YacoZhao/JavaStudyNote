package my_code_sum_up.code_tree.zuo;

import my_code_sum_up.code_tree.util.PrintTree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// 有关树的问题测试
public class TreeTest {

    // 获取最大的活跃值
    public static int getMaxHappy(int[][] matrix){
        // 创建一个二维数组存储活跃度信息
        int[][] dp = new int[matrix.length][2];
        // 第一个元素表示该员工来的活跃度，第二个元素表示员工不来的活跃度
        boolean[] visited = new boolean[matrix.length];  // 创建数组，表示当前员工是否已经访问过
        // 遍历martix，找出大boss的位置
        int boss = 0;
        for (int i = 0; i < matrix.length; i++) {
            if(i == matrix[i][0]){
                boss = i;
                break;
            }
        }
        // 进入递归返回最大
        process(matrix,dp,visited,boss);
        return Math.max(dp[boss][0],dp[boss][1]);
    }

    private static void process(int[][] matrix, int[][] dp, boolean[] visited, int boss) {
        visited[boss] = true;       // 计算boss的信息
        dp[boss][0] = matrix[boss][1];  // 赋值活跃度
        for (int i = 0; i < matrix.length; i++) {
            if(matrix[i][0] == boss && !visited[boss]){
                process(matrix, dp, visited, i);
                dp[boss][0] += dp[i][1];
                dp[boss][1] += Math.max(dp[i][0],dp[i][1]);
            }
        }
    }
}
