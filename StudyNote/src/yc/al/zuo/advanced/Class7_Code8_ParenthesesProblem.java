package yc.al.zuo.advanced;

import org.omg.CORBA.PUBLIC_MEMBER;

// 7-8 最长有效括号
public class Class7_Code8_ParenthesesProblem {

    //问题一： 给定一个字符串str，判断是不是整体有效的括号字符串。
    public static boolean isValid(String str){
        if(str == null || "".equals(str)) return false;
        char[] chars = str.toCharArray();
        int statue = 0;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] != '(' && chars[i] == ')'){
                return false;
            }
            if(chars[i] == ')' && --statue < 0){
                return false;
            }
            if(chars[i] == '('){
                statue++;
            }
        }
        return statue == 0;
    }

    // 问题二————给定一个括号字符串str，返回最长的有效括号子串。
    public static int maxLength(String str){
        if(str == null || "".equals(str)) return 0;
        char[] chars = str.toCharArray();
        int[] dp = new int[chars.length];
        int pre = 0;
        int res = 0;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == ')'){
                pre = i - dp[i - 1] - 1;
                if(pre > 0 && chars[pre] == '('){
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            res = Math.max(res,dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        String str1 = "((())())";
        System.out.println(isValid(str1));
        System.out.println(maxLength(str1));

        String str2 = "(())(()(()))";
        System.out.println(isValid(str2));
        System.out.println(maxLength(str2));

        String str3 = "()(()()(";
        System.out.println(isValid(str3));
        System.out.println(maxLength(str3));
    }
}
