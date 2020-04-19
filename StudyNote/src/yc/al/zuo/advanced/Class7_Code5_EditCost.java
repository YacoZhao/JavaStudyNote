package yc.al.zuo.advanced;

// 7-5 最小编辑代价
public class Class7_Code5_EditCost {

    /**
     * 方法一： 暴力递归
     * @param str1
     * @param str2
     * @param ic 新增代价
     * @param dc 删除代价
     * @param rc 替换代价
     * @return
     */
    public static int minCost1(String str1, String str2, int ic, int dc, int rc) {
        if (str1 == null || str2 == null) {
            return 0;
        }
        return getAns(str1,str2,0,0,ic,dc,rc);
    }

    private static int getAns(String str1, String str2, int c1, int c2, int ic, int dc, int rc) {
        if(c1 == str1.length()){
            return (str2.length() - c2) * ic;
        }
        if(c2 == str2.length()){
            return (str1.length() - c1) * dc;
        }
        if(str1.charAt(c1) == str2.charAt(c2)){
            return getAns(str1, str2, c1+1, c2+1, ic, dc, rc);
        }else{
            int i = ic + getAns(str1, str2, c1, c2+1, ic, dc, rc);
            int d = dc + getAns(str1, str2, c1+1, c2, ic, dc, rc);
            int r = rc + getAns(str1, str2, c1+1, c2+1, ic, dc, rc);
            return Math.min(Math.min(i,d),r);
        }
    }

    /**
     * 方法二： 暴力递归改动态规划
     * @param str1
     * @param str2
     * @param ic
     * @param dc
     * @param rc
     * @return
     */
    public static int minCost2(String str1, String str2, int ic, int dc, int rc) {
        if (str1 == null || str2 == null) {
            return 0;
        }
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i <= str1.length(); i++) {
            for (int j = 0; j <= str2.length(); j++) {
                if(i == 0){
                    dp[i][j] = ic * j;
                }else if(j == 0){
                    dp[i][j] = dc * i;
                }else {
                    if(str1.charAt(i - 1) == str2.charAt(j - 1)){
                        dp[i][j] = dp[i-1][j-1];
                    }else{
                        dp[i][j] = Math.min(Math.min(dp[i-1][j] + dc,dp[i][j-1] + ic),dp[i-1][j-1] + rc);
                    }
                }
            }
        }
        return dp[str1.length()][str2.length()];
    }

    // +++++++++++++++++++++++++++测试集+++++++++++++++++++++++++++++++++++++
    public static void main(String[] args) {
        String str1 = "ab12cd3";
        String str2 = "abcdf";
        System.out.println(minCost1(str1, str2, 5, 3, 2));
        System.out.println(minCost2(str1, str2, 5, 3, 2));

        str1 = "abcdf";
        str2 = "ab12cd3";
        System.out.println(minCost1(str1, str2, 3, 2, 4));
        System.out.println(minCost2(str1, str2, 3, 2, 4));

        str1 = "";
        str2 = "ab12cd3";
        System.out.println(minCost1(str1, str2, 1, 7, 5));
        System.out.println(minCost2(str1, str2, 1, 7, 5));

        str1 = "abcdf";
        str2 = "";
        System.out.println(minCost1(str1, str2, 2, 9, 8));
        System.out.println(minCost2(str1, str2, 2, 9, 8));
    }
}
