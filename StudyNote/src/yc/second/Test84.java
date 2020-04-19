package yc.second;

import java.util.Stack;

public class Test84 {

    /**
     * 方法一： 暴力解法(超时)
     * @param heights
     * @return
     */
    public int largestRectangleArea1(int[] heights) {
        int res = 0;
        if (heights.length == 0) {
            return res;
        }
        for (int i = 0; i < heights.length; i++) {
            for (int j = i; j < heights.length; j++) {
                // 从i到j位置找出最低的数组元素，然后乘上长度更新最终的res
                int min_target = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    min_target = Math.min(min_target, heights[k]);
                }
                res = Math.max(res, min_target * (j - i + 1));
            }
        }
        return res;
    }

    /**
     * 解法二：优化暴力法
     * @param heights
     * @return
     */
    public int largestRectangleArea2(int[] heights) {
        int res = 0;
        if (heights.length == 0) {
            return res;
        }
        for (int i = 0; i < heights.length; i++) {
            int min_target = Integer.MAX_VALUE;
            for (int j = i; j < heights.length; j++) {
                min_target = Math.min(min_target, heights[j]);
                res = Math.max(res, min_target * (j - i + 1));
            }
        }
        return res;
    }

    /**
     * 解法三：分治算法
     * @param heights
     * @return
     */
    public int largestRectangleArea3(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        return calculate(heights,0,heights.length -1);
    }

    /**
     * 分治过程
     * @param heights
     * @param start
     * @param end
     * @return
     */
    private int calculate(int[] heights, int start, int end){
        if (start > end) {
            return 0;
        }
        int min_index = start;
        for (int i = start; i <= end; i++) {
            if (heights[i] < heights[min_index]) {
                min_index = i;
            }
        }
        return Math.max(heights[min_index] * (end - start + 1),
                Math.max(calculate(heights, start, min_index - 1),
                        calculate(heights, min_index + 1, end)));
    }


    /**
     * 解法四: 栈求解
     * @param heights
     * @return
     */
    public int largestRectangleArea4(int[] heights) {
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
                    // 栈保持递增的规律
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
