package yc.second;

public class Test91 {
    /**
     * 动态规划求解
     * 思路分析：
     * 递归和动态规划两种解法。本题解讲述如何从递推转换成动态规划。
     * 从后往前遍历。如果
     * 以22067为例，从后往前遍历。
     * 首先如果为7。很显然是1种7->G
     * 如果为67。很显然还是1种67->FG
     * 如果为067。结果为0。
     * 如果为2067。 结果为numDecodings（20 67）+ numDecodings（2 067）= numDecodings（20 67）->TFG
     * 如果为22067。 结果为numDecodings（2 2067）+ numDecodings（22 067）= numDecodings（2 2067）->BTFG
     * 从中，我们可以看出规律。
     * 如果开始的数为0，结果为0。
     * 如果开始的数加上第二个数小于等于26。结果为 numDecodings（start+1）+ numDecodings（start +2）
     * 如果开始的数加上第二个数大于26。结果为 numDecodings（start +1）
     *
     * 作者：reedfan
     * 链接：https://leetcode-cn.com/problems/decode-ways/solution/java-di-gui-dong-tai-gui-hua-kong-jian-ya-suo-by-r/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[len] = 1;
        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                dp[i] = 0;
            } else if (i < len - 1 && ((s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0')) <= 26) {
                dp[i] = dp[i + 1] + dp[i + 2];
            } else {
                dp[i] = dp[i + 1];
            }
        }
        return dp[0];
    }
}
