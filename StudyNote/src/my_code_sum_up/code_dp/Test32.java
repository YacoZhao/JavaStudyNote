package my_code_sum_up.code_dp;

import java.util.Stack;

//32. 最长有效括号
// 方法一： 暴力破解
// 方法二： 动态规划
public class Test32 {

    /**
     * 方法一： 暴力破解(超时)
     * @param s
     * @return
     */
    public int longestValidParentheses1(String s) {
        if(s.equals("") || s.length() < 2) return 0;
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n - 1; i++) {
            if(s.charAt(i) == '('){  // 只有以'('开始才执行
                for (int j = i + 2; j <= n; j = j + 2) {
                    if(isValid(s.substring(i,j))){
                        ans = Math.max(ans,j - i);
                    }
                }
            }
        }
        return ans;
    }

    // 编写一个函数，判断一个字符串是否是有效括号字符串
    // 基本思想： 遇到左括号则入栈，遇到右括号则查看栈顶元素是否为左括号，缶则匹配不上直接返回F，遍历结束之后，检查栈是否为空
    private boolean isValid(String s){
        // 创建辅助栈
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '('){
                stack.push(c);
            }else if(!stack.isEmpty() && stack.peek() == '('){
                stack.pop();
            }else{
                return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 方法二： 动态规划
     * @param s
     * @return
     */
    public int longestValidParentheses2(String s) {
        if(s.equals("") || s.length() < 2) return 0;
        int ans = 0;
        int n = s.length();
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            if(s.charAt(i) == ')'){
                if(s.charAt(i - 1) == '('){
                    dp[i] = ((i - 2) > 0 ? dp[i - 2] : 0) + 2;
                }else{
                    if(i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '('){
                        dp[i] = (i - dp[i - 1] - 2 > 0 ? dp[i - dp[i - 1] - 2] : 0) + dp[i - 1] + 2;
                    }
                }
            }
            ans = Math.max(ans,dp[i]);
        }
        return ans;
    }
}
