package yc.al.zuo.advanced;

// 5-4 最大活跃度问题
public class Class5_Code4_MaxHappy {

    // 找出最大的boss来或者不来的活跃度
    public static int maxHappy(int[][] matrix) {
        // 创建dp数组，存储当前员工来的活跃度和不来的活跃度
        int[][] dp = new int[matrix.length][2];
        boolean[] visited = new boolean[matrix.length];
        int root = 0;  // 最大的boss
        for (int i = 0; i < matrix.length; i++) {
            if(i == matrix[i][0]){
                root = i;
            }
        }
        process(matrix, dp, visited, root);
        // 返回大boss来的活跃度和不来的活跃度中取最大的值
        return Math.max(dp[root][0],dp[root][1]);
    }

    private static void process(int[][] matrix, int[][] dp, boolean[] visited, int root) {
        visited[root] = true;
        dp[root][1] = matrix[root][1];   // 初始化来的活跃u度
        for (int i = 0; i < matrix.length; i++) {
            if(matrix[i][0] == root && !visited[i]){
                process(matrix,dp,visited,i);
                dp[root][1] += dp[i][0];
                dp[root][0] += Math.max(dp[i][0],dp[i][1]);
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 8 }, { 1, 9 }, { 1, 10 } };
        System.out.println(maxHappy(matrix));
    }
}
