package yc.second;

import java.util.Stack;

public class Test85 {
    // 暴力破解方法;
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        int row = matrix.length;
        int col = matrix[0].length;
        //创建辅助数组，记录每一行中以当前位置为1时，连续的1有多少
        int[][] dp = new int[row][col];
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(matrix[i][j] == '1'){
                    if(j == 0){
                        dp[i][j] = 1;
                    }else{
                        dp[i][j] = dp[i][j - 1] + 1;
                    }
                }
                // 创建一个指针，记录当前的最小宽度
                int minWidth = dp[i][j];
                // 向上扩展行
                for (int k = i; k >= 0; k--) {
                    // 更新当前的高度，1 - col
                    int height = i - k + 1;
                    // 更新最小的宽度
                    minWidth = Math.min(minWidth,dp[k][j]);
                    // 更新面积
                    res = Math.max(res,height * minWidth);
                }
            }
        }
        return res;
    }

    // 解法二: 基于84题的栈求解优化
    public int maximalRectangle2(char[][] matrix){
        if(matrix.length == 0) return 0;
        int maxArea = 0;
        int[] heights = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == '1'){
                    heights[j]++;
                }else{
                    heights[j] = 0;
                }
            }
            maxArea = Math.max(maxArea,process(heights));
        }
        return maxArea;
    }

    private int process(int[] heights){
        if(heights == null || heights.length == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int p = 0;
        int len = heights.length;
        while(p < len){
            if(stack.isEmpty()){
                stack.push(p);
                p++;
            }else{
                int top = stack.peek();
                if(heights[p] >= heights[top]){
                    stack.push(p);
                    p++;
                }else{
                    // 保存当前栈顶的高度
                    int height = heights[stack.pop()];
                    int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                    int rightIndex = p;
                    maxArea = Math.max(maxArea,(rightIndex - leftIndex - 1) * height);
                }
            }
        }
        while(!stack.isEmpty()){
            // 保存当前栈顶的高度
            int height = heights[stack.pop()];
            int leftIndex = stack.isEmpty() ? -1 : stack.peek();
            maxArea = Math.max(maxArea,(len - leftIndex - 1) * height);
        }
        return maxArea;
    }
}
