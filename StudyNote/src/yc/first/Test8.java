package yc.first;

//8. 字符串转换整数 (atoi)
public class Test8 {

    //java-使用long型来确保结果不溢出
    public int myAtoi(String str) {
        int ans = 0;
        int zf = 1;
        char[] chars = str.toCharArray();
        int i = 0;
        for (; i < chars.length; i++) {
            char ch = chars[i];
            if (ch == ' ') {
                continue;
            } else {
                if (ch == '-') {
                    i++;
                    zf = -1;
                    break;
                } else if (ch == '+') {
                    i++;
                    break;
                } else if (ch < '0' || ch > '9') {
                    return 0;
                } else {
                    break;
                }
            }
        }

        if (i == chars.length) {
            return 0;
        }

        for (; i < chars.length; i++) {
            if (chars[i] < '0' || chars[i] > '9') {
                return ans;
            }
            int pop = (chars[i] - 48) * zf;
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && pop > 7)) {
                return Integer.MAX_VALUE;
            }
            if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && pop < -8)) {
                return Integer.MIN_VALUE;
            }
            ans = ans * 10 + pop;
        }
        return ans;
    }
}
