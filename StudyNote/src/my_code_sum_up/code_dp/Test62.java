package my_code_sum_up.code_dp;

//62. 不同路径
// 方法一： 暴力递归
// 方法二： 回溯
// 方法三： 动态规划
public class Test62 {

    /**
     * 暴力递归
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths1(int m, int n) {
        return going(0,0,m - 1,n - 1);
    }

    private static int going(int s_r,int s_c, int m,int n){
        if(s_r == m || s_c == n){
            return 1;
        }
        int ans = 0;
        ans = going(s_r+1,s_c,m,n) + going(s_r,s_c+1,m,n);
        return ans;
    }
    /**
     * 动态规划求解
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths2(int m, int n) {
        //1. 边界条件判断
        if (m == 1 || n == 1) {
            return 1;
        }
        //2. 创建存储对象的数组dp[i][j]: 表示从起点走到i，j这个点右多少种路径走法
        int[][] dp = new int[m][n];
        //3. 初始化路径
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = 1;
        }
        //4. 遍历数组
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        //5. 返回结果
        return dp[m - 1][n - 1];
    }

    // 优化暴力递归(改一维数组)
    public static int uniquePaths3(int m, int n) {
        //1. 边界条件判断
        if (m == 1 || n == 1) {
            return 1;
        }
        //2. 创建存储对象的数组dp[i][j]: 表示从起点走到i，j这个点右多少种路径走法
        int[] dp = new int[n];
        //3. 遍历数组
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(i == 0){
                    dp[j] = 1;
                }else if(j == 0){
                    dp[j] = 1;
                }else{
                    dp[j] = dp[j] + dp[j - 1];
                }
            }
        }
        //5. 返回结果
        return dp[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths1(10,10));
        System.out.println(uniquePaths2(10,10));
    }
}
