package my_code_sum_up.code_dp;

import javax.swing.text.MaskFormatter;
import java.util.Stack;

// 85. 最大矩形
// 方法一： 暴力破解
// 方法二： 基于84题的栈的求解方法
public class Test85 {

    /**
     * 方法一： 暴力破解法(时间复杂度： M * N * N)
     * @param matrix
     * @return
     */
    public int maximalRectangle1(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] width = new int[row][col];
        int maxArea = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    if (j == 0) {
                        width[i][j] = 1;
                    } else {
                        width[i][j] = width[i][j - 1] + 1;
                    }
                } else {
                    width[i][j] = 0;
                }
                // 记录当前列中的最小值
                int minWidth = width[i][j];
                for (int k = i; k >= 0; k--) {
                    int height = i - k + 1;
                    // 找出最小的值作为宽度
                    minWidth = Math.min(minWidth, width[k][j]);
                    // 更新面积
                    maxArea = Math.max(maxArea, height * minWidth);
                }
            }
        }
        return maxArea;
    }

    /**
     * 解法二: 基于84题的栈求解优化(时间复杂度（M * N）)
     * @param matrix
     * @return
     */
    public int maximalRectangle2(char[][] matrix){
        if (matrix.length == 0) return 0;
        int maxArea = 0;
        int[] height = new int[matrix[0].length];
        for (char[] chars : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (chars[j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }
            maxArea = Math.max(maxArea, getCurMax(height));
        }
        return maxArea;
    }

    private int getCurMax(int[] arr){
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int p = 0;
        int height = 0;
        int leftLessMin = 0;
        int rightLessMin = 0;
        while(p < arr.length){
            if(stack.isEmpty()){
                stack.push(p);
                p++;
            }else{
                int top = stack.peek();
                if(arr[p] >= arr[top]){
                    stack.push(p);
                    p++;
                }else{
                    height = arr[stack.pop()];
                    leftLessMin = stack.isEmpty() ? -1 : stack.peek();
                    rightLessMin = p;
                    maxArea = Math.max(maxArea,height * (rightLessMin - leftLessMin - 1));
                }
            }
        }
        while(!stack.isEmpty()){
            height = arr[stack.pop()];
            leftLessMin = stack.isEmpty() ? -1 : stack.peek();
            rightLessMin = arr.length;
            maxArea = Math.max(maxArea,(rightLessMin - leftLessMin - 1) * height);
        }
        return maxArea;
    }
}
