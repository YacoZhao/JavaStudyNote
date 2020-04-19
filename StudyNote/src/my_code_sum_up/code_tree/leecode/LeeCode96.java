package my_code_sum_up.code_tree.leecode;

//96. 不同的二叉搜索树(动态规划——有点意思)
public class LeeCode96 {

    // 1-n组成的二叉搜索树一共有多少种（找规律）
    public int numTrees(int n) {
        if (n < 2) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            for (int j = i - 1; j >= 0; j--) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }
        return dp[n];
    }

}
