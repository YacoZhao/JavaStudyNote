package my_code_sum_up.code_dp;

// 寻找最长的回文字串（中等）
// 方法一： 动态规划，记录最长回文字串的长度（N2）
// 方法二： 中心扩展算法                 （N2）
// 方法三： 马拉车算法                   （N）
public class Test5 {

    /**
     * 方法一： 动态规划
     * @param s
     * @return
     */
    public String longestPalindrome1(String s) {
        if(s.equals("")) return s;
        // 将字符串s反转
        String r = new StringBuilder(s).reverse().toString();
        // 创建dp数组
        int len = s.length();
        int[][] dp = new int[len + 1][len + 1];
        // 初始化，当s和r任何一方位空字符串时，最长回文串的长度都为0，数组默认为0，所以不用初始化
        int maxLen = 0;
        int maxEnd = 0;
        // 遍历填数组
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if(s.charAt(i) == r.charAt(j)){
                    dp[i+1][j+1] = dp[i][j] + 1;
                    if(dp[i+1][j+1] > maxLen){
                        if(len - j - 1 + dp[i+1][j+1] - 1 == i){
                            maxLen = dp[i+1][j+1];
                            maxEnd = i;
                        }
                    }
                }
            }
        }
        // 返回结果
        return s.substring(maxEnd - maxLen + 1,maxEnd + 1);
    }

    // dp二位数组该一维数组(反过来填数组即可)（空间高度优化）
    public String longestPalindrome2(String s) {
        if(s.equals("")) return s;
        // 将字符串s反转
        String r = new StringBuilder(s).reverse().toString();
        // 创建dp数组
        int len = s.length();
        int[] dp = new int[len + 1];
        // 初始化，当s和r任何一方位空字符串时，最长回文串的长度都为0，数组默认为0，所以不用初始化
        int maxLen = 0;
        int maxEnd = 0;
        // 遍历填数组
        for (int i = 0; i < len; i++) {
            for (int j = len - 1; j >= 0; j--) {
                if(s.charAt(i) == r.charAt(j)){
                    dp[j+1] = dp[j] + 1;
                    if(dp[j+1] > maxLen){
                        if(len - j - 1 + dp[j+1] - 1 == i){
                            maxLen = dp[j+1];
                            maxEnd = i;
                        }
                    }
                }else{
                    dp[j+1] = 0;  //缶则当前位置的元素需要置0
                }
            }
        }
        // 返回结果
        return s.substring(maxEnd - maxLen + 1,maxEnd + 1);
    }


    /**
     * 方法二：  中心扩展算法(时间成本降低)
     * @param s
     * @return
     */
    public String longestPalindrome3(String s) {
        if(s.equals("")) return s;
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = enlarge(s,i,i);
            int len2 = enlarge(s,i,i+1);
            int len = Math.max(len1,len2);
            if(len > end - start){
                start = i - (len - 1) / 2;
                end = i + (len / 2);
            }
        }
        return s.substring(start,end + 1);
    }

    // 中心向外扩展
    private int enlarge(String s, int left, int right){
        int l = left;
        int r = right;
        while(l >= 0 && r <= s.length() - 1 && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
        }
        return r - l - 1;
    }

    /**
     * 方法三： Manacher's Algorithm 马拉车算法
     * @param s
     * @return
     */
    public String longestPalindrome4(String s) {
        if(s.equals("")) return s;
        String ret = tackleString(s);
        int n = ret.length();
        int[] bj = new int[n];   // 存放每个位置的回文半径
        int maxR = -1;           // 存放当前的最大回文右边界
        int cC = -1;             // 存放当前最大回文右边界对应的回文中心
        // 遍历s字符串
        for (int i = 0; i < n; i++) {
            // 如果当前位置在最大回文右边界内，则在当前位置与右边界之间的距离和前半部分的回文半径中去最小值
            // 如果在边界或者maxR外边，默认半径为1，然后进行中心扩展
            bj[i] = maxR > i ? Math.min(maxR - i,bj[(2 * cC) - i]) : 1;
            // 中心扩展
            while(i - bj[i] >= 0 && i + bj[i] < n){
                if(ret.charAt(i - bj[i]) == ret.charAt(i + bj[i])){
                    bj[i]++;
                }else{
                    break;
                }
            }
            // 扩展结束之后，更新maxR和cC
            if(i + bj[i] > maxR){
                maxR = i + bj[i];
                cC = i;
            }
        }
        // 找出当前半径数组中的最大半径
        int maxLen = bj[0];
        int center = 0;
        for (int i = 1; i < bj.length; i++) {
            if(bj[i] > maxLen){
                maxLen = bj[i];
                center = i;
            }
        }
        // 有了最大的回文半径和最大的回文中心，返回字符串即可
        int start = (center - maxLen + 1) / 2;
        int end = (center + maxLen) / 2;
        return s.substring(start,end);
    }

    // 改造字符串————给字符串加上虚轴
    private String tackleString(String s) {
        int n = s.length();
        String ret = "#";
        for (int i = 0; i < n; i++) {
            ret += s.charAt(i) + "#";
        }
        return ret;
    }
}
