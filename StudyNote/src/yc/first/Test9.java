package yc.first;

//9. 回文数（简单）
public class Test9 {

    //方法一： 反转字符串
    public boolean isPalindrome1(int x) {
        String s = x + "";
        String r = new StringBuilder(s).reverse().toString();
        return s.equals(r);
    }

    //方法二： 两边向里推
    public boolean isPalindrome2(int x) {
        String s = x + "";
        int len = s.length();
        for (int i = 0; i < len/2; i++) {
            if(s.charAt(i) != s.charAt(len - 1 - i)){
                return false;
            }
        }
        return true;
    }

    //方法三： 数学解法
    public boolean isPalindrome3(int x) {
        if (x < 0) {
            return false;
        }
        int div = 1;
        while (x / div >= 10) {
            div = div * 10;
        }
        while (x > 0) {
            int left = x / div;
            int right = x % 10;
            if (left != right) {
                return false;
            }
            x = (x % div) / 10;
            div = div / 100;
        }
        return true;
    }

    //方法四： 取出后半段数字进行翻转。
    public boolean isPalindrome4(int x) {
        //思考：这里大家可以思考一下，为什么末尾为 0 就可以直接返回 false
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;
        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        return x == revertedNumber || x == revertedNumber / 10;
    }
}
