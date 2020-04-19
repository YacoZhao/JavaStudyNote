package yc.second;

public class Test66 {
    /**
     * 数组（自己写的方法）
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        if(digits[len-1] < 9){
            digits[len-1]++;
            return digits;
        }
        for (int i = len - 1; i >= 0 ; i--) {
            digits[i] = 0;
            if (i > 0 && digits[i - 1] != 9) {
                digits[i - 1]++;
                return digits;
            }
        }
        int[] res = new int[len+1];
        res[0] = 1;
        return res;
    }

    /**
     * 差不多的写法
     * @param digits
     * @return
     */
    public int[] plusOne2(int[] digits) {
        for(int i = digits.length - 1; i >= 0; i--){
            digits[i]++;
            digits[i] = digits[i] % 10;
            if(digits[i] != 0){
                return digits;
            }
        }
        digits =  new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}
