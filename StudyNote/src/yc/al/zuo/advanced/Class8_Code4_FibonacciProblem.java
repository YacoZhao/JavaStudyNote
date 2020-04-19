package yc.al.zuo.advanced;

// 8-4 斐波那契数列引发出来的相关问题
public class Class8_Code4_FibonacciProblem {

    // 问题一：给定整数N，但会斐波那契数列的第N项
    // 方法一： 暴力递归
    public static int f1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return f1(n - 1) + f1(n - 2);
    }

    // 方法二： 迭代求解
    public static int f2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int c1 = 1;
        int c2 = 1;
        int res = 0;
        for (int i = 3; i <= n; i++) {
            res = c1 + c2;
            c1 = c2;
            c2 = res;
        }
        return res;
    }

    // 方法三： 动态规划
    public static int f3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }

    // 问题二：给定整数N，代表台阶数，一次可以跨2个或者1个台阶，返回有多少种走法。
    // 方法一： 暴力递归
    public static int s1(int n) {
        if (n <= 1) return 1;
        return s1(n - 1) + s1(n - 2);
    }

    // 方法二：迭代求解
    public static int s2(int n) {
        if (n <= 1) return 1;
        int c1 = 1;
        int c2 = 1;
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = c1 + c2;
            c1 = c2;
            c2 = res;
        }
        return res;
    }

    // 方法三： 动态规划
    public static int s3(int n) {
        if (n <= 1) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // 问题三： 小牛问题
    // 方法一： 暴力递归
    public static int m1(int n) {
        if (n < 5) return n;
        return m1(n - 1) + m1(n - 3);   // 去年的小牛数，加上3年钱的小牛数
    }

    // 方法二： 动态规划
    public static int m2(int n) {
        if (n < 5) return n;
        int[] dp = new int[n];
        for (int i = 0; i < 4; i++) {
            dp[i] = i + 1;
        }
        for (int i = 4; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 3];
        }
        return dp[n - 1];
    }

    // 测试集
    public static void main(String[] args) {
        System.out.println("f1 :" + f1(20));
        System.out.println("f2 :" + f2(20));
        System.out.println("f3 :" + f3(20));

        System.out.println("s1 :" + s1(20));
        System.out.println("s2 :" + s2(20));
        System.out.println("s3 :" + s3(20));

        System.out.println("m1 :" + m1(20));
        System.out.println("m2 :" + m2(20));
    }
}
