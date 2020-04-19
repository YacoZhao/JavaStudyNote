package yc.first;

//29. 两数相除
public class Test29 {

    /**
     * 补码除法
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        int ans = -1;
        int sign = 1;
        //除数和被除数都化为负数来处理，可以避免Integer.MIN_VALUE带来的麻烦
        if(dividend > 0){
            dividend = opposite(dividend);
            sign = opposite(sign);
        }
        if(divisor > 0){
            divisor = opposite(divisor);
            sign = opposite(sign);
        }
        //保存原来的储数和被除数，用于递归操作
        int pre_divided = dividend;
        int pre_divisor = divisor;
        //如过当前的除数被被除数大，则直接返回0（因为这里已经取反了）
        if(dividend > divisor){
            return 0;
        }
        //因为ans初始化为-1；所以首先将除数先减去一个被除数
        dividend -= divisor;
        //互换查找，不停的翻倍ans，和divisor
        while(dividend <= divisor){
            ans = ans + ans;
            divisor += divisor;
            dividend -= divisor;
        }
        //采用递归的形式求解结果（当前的结果 + 后面的结果）
        //注意： 因为后面结果输出经过下面的if/else判断转化成了正数，所以这里还是需要取反
        int a = ans + opposite(divide(pre_divided - divisor,pre_divisor));
        if(a == Integer.MIN_VALUE){
            if(sign > 0){
                return Integer.MAX_VALUE;
            }else{
                return Integer.MIN_VALUE;
            }
        }else{
            if(sign > 0){
                return opposite(a);
            }else{
                return a;
            }
        }
    }

    /**
     * 取相反数的操作
     * @param x
     * @return
     */
    private int opposite(int x){
        return ~x + 1;   //先求补码，然后再加1
    }
}
