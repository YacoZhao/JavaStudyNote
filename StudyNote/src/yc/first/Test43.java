package yc.first;

//字符串相乘
public class Test43 {

    /**
     * 字符串相乘
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        //1. 首先进行特殊情况分析
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        //2. 设置存储最终结果的变量
        String res = "0";
        //3. for循环遍历nums1的字符，从最后一位开始
        for (int i = num1.length() - 1; i >= 0; i--) {
            //3.1 获取当前字符
            int cur = num1.charAt(i) - '0';
            //3.2 设置进位符
            int carry = 0;
            //3.3 设置存储计算结果的StringBuilder
            StringBuilder tep = new StringBuilder();
            //3.4 计算之前先给结果补零，个位不补，十位补一个零，百位补两个零
            for (int j = 0; j < num1.length() - i - 1; j++) {
                tep.append("0");
            }
            //3.5 将num1中取出的n与num2相乘
            for (int k = num2.length() - 1; k >= 0 || carry != 0; k--) {
                int x = k < 0 ? 0 : num2.charAt(k) - '0';
                int sum = (x * cur + carry) % 10;
                tep.append(sum);
                carry = (x * cur + carry) / 10;
            }
            //3.6 将tep中的字符串与当前的res相加
            res = addString(res, tep.reverse().toString());
        }
        //4. 返回结果
        return res;
    }

    /**
     * 相加两个字符串
     * @param s1
     * @param s2
     * @return
     */
    private String addString(String s1, String s2) {
        //1. 首先设置进位符
        int carry = 0;
        //2. 设置结果变量StringBuilder
        StringBuilder ans = new StringBuilder();
        //3. for循环到两个字符串指针都为负，且进位符为0时结束
        for (int i = s1.length() - 1, j = s2.length() - 1; i >= 0 || j >= 0 || carry != 0; i--, j--) {
            int x = i < 0 ? 0 : s1.charAt(i) - '0';
            int y = j < 0 ? 0 : s2.charAt(j) - '0';
            int sum = (x + y + carry) % 10;
            ans.append(sum);
            carry = (x + y + carry) / 10;
        }
        //4. 返回结果
        return ans.reverse().toString();
    }

    /**
     * 方法二： 对竖式的一种优化
     * @param num1
     * @param num2
     * @return
     */
    public String multiply2(String num1, String num2) {
        //1. 首先进行特殊情况分析
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        //2. 创建用于存储中间值的数组
        int[] temp = new int[num1.length() + num2.length()];
        //3. 循环num1
        for (int i = num1.length() - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                int sum = (temp[i + j + 1] + x * y);
                temp[i + j + 1] = sum % 10;
                temp[i + j] += sum / 10;      //注意这里是在原来的基础上相加
            }
        }
        //4. 遍历列表，将结果到处
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < temp.length; i++) {
            if (i == 0 && temp[i] == 0) {
                continue;
            }
            ans.append(temp[i]);
        }
        return ans.toString();
    }
}
