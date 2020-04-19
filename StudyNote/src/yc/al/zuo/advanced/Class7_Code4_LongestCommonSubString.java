package yc.al.zuo.advanced;

import javax.swing.text.MaskFormatter;

// 7-4 最长公共子串
public class Class7_Code4_LongestCommonSubString {

    // 方法一： 构建动态规划
    public static String longestCommonSubString1(String str1, String str2) {
        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
            return "";
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int[][] dp = getDpInfo(chars1, chars2);
        // 遍历dp数组，找出最大值
        int max = 0;
        int end = 0;
        for (int i = 0; i < chars1.length; i++) {
            for (int j = 0; j < chars2.length; j++) {
                if (dp[i][j] > max) {
                    end = i;
                    max = dp[i][j];
                }
            }
        }
        return str1.substring(end - max + 1, end + 1);
    }

    private static int[][] getDpInfo(char[] chars1, char[] chars2) {
        int[][] dp = new int[chars1.length][chars2.length];
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] == chars2[0]) {
                dp[i][0] = 1;
            }
        }
        for (int j = 0; j < chars2.length; j++) {
            if (chars1[0] == chars2[j]) {
                dp[0][j] = 1;
            }
        }
        for (int i = 1; i < chars1.length; i++) {
            for (int j = 1; j < chars2.length; j++) {
                if (chars1[i] == chars2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }
        return dp;
    }

    // 方法二： 迭代（时间复杂度O(M*N)  空间复杂度O(1)）
    public static String longestCommonSubString2(String str1, String str2){
        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
            return "";
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int c1 = 0;    // str1指针，从0增长到尾部
        int c2 = chars2.length - 1;  // str2指针，从尾部递减至0
        int max = 0;
        int end = 0;
        while(c1 < chars1.length){
            int i = c1;
            int j = c2;
            int len = 0;
            while(i < chars1.length && j < chars2.length){
                if(chars1[i] == chars2[j]){
                    len++;
                }else{
                    len = 0;
                }
                if(len > max){
                    max = len;
                    end = i;
                }
                i++;
                j++;
            }
            if(c2 > 0){
                c2--;
            }else{
                c1++;
            }
        }
        return str1.substring(end - max + 1, end + 1);
    }

    // +++++++++++++++++++++++创建测试集++++++++++++++++++++++++++++++
    public static void main(String[] args) {
        String str1 = "ABC1234567DEFG";
        String str2 = "HIJKL1234567MNOP";
        System.out.println(longestCommonSubString1(str1, str2));
        System.out.println(longestCommonSubString2(str1, str2));
    }
}
