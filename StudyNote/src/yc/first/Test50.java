package yc.first;

//50. Pow(x, n)
public class Test50 {
    /**
     * 方法一： 暴力（超时）
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        for (long i = 0; i < N; i++)
            ans = ans * x;
        return ans;
    }

//    /**
//     * 方法二： 递归()
//     * @param x
//     * @param n
//     * @return
//     */
//    public double myPow2(double x, int n) {
//        if (n == 0) {
//            return 1.0;
//        }
//        long N = n;
//        if (N < 0) {
//            x = 1 / x;
//            N = -N;
//        }
//        return fastPow(x, N);
//    }
//
//    private double fastPow(double x, long n) {
//        if (n == 0) {
//            return 1.0;
//        }
//        Double half = fastPow(x, n / 2);
//        if (n % 2 == 0) {
//            return half * half;
//        } else {
//            return half * half * x;
//        }
//    }

    //二刷
    public double myPow2(double x, int n){
        if(n == 0){
            return 1.0;
        }
        if(n < 0){
            x = 1 / x;
            n = ~n + 1;
        }
        return fastPow(x,n);
    }

    private double fastPow(double x, int n) {
        if(n == 0){
            return 1.0;
        }
        double mid = fastPow(x,n/2);
        if((n % 2 == 0)){
            return mid * mid;
        }else{
            return mid * mid * x;
        }
    }
}
