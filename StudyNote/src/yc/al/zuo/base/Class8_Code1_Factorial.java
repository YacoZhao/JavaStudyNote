package yc.al.zuo.base;

/**
 * 斐波那契数列
 * @author code_yc
 * @version 1.0
 * @date 2020/4/25 8:37
 */
public class Class8_Code1_Factorial {

    // 递归解法
    public static long getFactorial1(int n) {
        if(n == 1) return 1L;
        return (long) n * getFactorial1(n - 1);
    }

    // 非递归解法
    public static long getFactorial2(int n){
        long res = 1L;
        for (int i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.println(getFactorial1(n));
        System.out.println(getFactorial2(n));
    }
}
