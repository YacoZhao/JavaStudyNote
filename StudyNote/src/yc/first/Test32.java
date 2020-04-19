package yc.first;

import java.util.Stack;

//32. 最长有效括号
public class Test32 {
    public static void main(String[] args) {
        System.out.println(longestValidParentheses("(()"));
    }

    //方法一： 暴力法（超时）
    public static int longestValidParentheses(String s) {
        int ans = 0;
        for(int i = 0; i< s.length();i++){
            for(int j = i + 2; j<=s.length();j+=2){
                if(isValid(s.substring(i,j))){
                    ans = Math.max(ans,j-i);
                }
            }
        }
        return ans;
    }

    //检查字符串是否为有效字符串
    private static boolean isValid(String s){
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i<s.length();i++){
            char ch = s.charAt(i);
            if(ch == '('){
                stack.push(ch);
            }else if(!stack.empty() && stack.peek() == '('){
                stack.pop();
            }else{
                return false;
            }
        }
        return stack.empty();
    }

    //方法二： 动态规划求解
    public int longestValidParentheses2(String s) {
        int ans = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i > 1 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] - 2 > 0 ? dp[i - dp[i - 1] - 2] : 0);
                }
                ans = Math.max(ans, dp[i]);
            }
        }
        return ans;
    }

    //方法三： 利用栈进行遍历求解
    public int longestValidParentheses3(String s) {
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    ans = Math.max(ans, i - stack.peek());
                }
            }
        }
        return ans;
    }

    //方法四： 双向遍历
    public int longestValidParentheses4(String s) {
        int ans = 0;
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (right > left) {
                left = 0;
                right = 0;
            }
            if (left == right) {
                ans = Math.max(ans, left + right);
            }
        }
        left = 0;
        right = 0;
        for (int j = s.length()-1; j >= 0; j--) {
            if (s.charAt(j) == ')') {
                right++;
            } else {
                left++;
            }
            if (left == right) {
                ans = Math.max(ans, left + right);
            }
            if (left > right) {
                left = 0;
                right = 0;
            }
        }
        return ans;
    }
}
