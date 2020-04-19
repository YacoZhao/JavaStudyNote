package yc.al.zuo.advanced;

// 回文串的最小分割数
public class Class7_Code6_PalindromeMinCut {

    public static int minCut(String str) {
        if(str == null || "".equals(str)) return 0;
        int res = 0;
        boolean[][] dp = new boolean[str.length()][str.length()];
        int[] cp = new int[str.length() + 1];
        cp[0] = 0;
        // 创建回文标识数组的同时，更新每个位置的最大分割次数
        for (int len = 1; len <= str.length(); len++) {
            for (int i = 0; i + len - 1 < str.length(); i++) {
                int j = i + len - 1;
                if(str.charAt(i) == str.charAt(j) && ((j - i < 3) || dp[i + 1][j - 1])){
                    dp[i][j] = true;
                    cp[j + 1] = cp[j + 1] == 0 ? cp[i] + 1 : Math.min(cp[j + 1],cp[i] + 1); // 更新当前的分割次数
                }else{
                    cp[j + 1] = Math.min(cp[j] + 1,cp[j + 1]);                              // 更新当前的分割次数
                }
            }
        }
        return cp[str.length()];
    }

    // 获取字符串的回文信息
    private static boolean[][] getInfo(String str) {
        boolean[][] dp = new boolean[str.length()][str.length()];
        for (int len = 1; len <= str.length(); len++) {
            for (int i = 0; i + len - 1 < str.length(); i++) {
                int j = i + len - 1;
                if(str.charAt(i) == str.charAt(j)){
                    dp[i][j] = (j - i < 3) || dp[i + 1][j - 1];
                }
            }
        }
        return dp;
    }

    // +++++++++++++++++++++++测试函数+++++++++++++++++++++++++++
    // for test
    public static String getRandomStringOnlyAToD(int len) {
        int range = 'D' - 'A' + 1;
        char[] charArr = new char[(int) (Math.random() * (len + 1))];
        for (int i = 0; i != charArr.length; i++) {
            charArr[i] = (char) ((int) (Math.random() * range) + 'A');
        }
        return String.valueOf(charArr);
    }

    public static void main(String[] args) {
        int maxLen = 10;
        int testTimes = 5;
        String str = null;
        for (int i = 0; i != testTimes; i++) {
            str = getRandomStringOnlyAToD(maxLen);
            System.out.print("\"" + str + "\"" + " : ");
            System.out.println(minCut(str));
        }
    }
}
