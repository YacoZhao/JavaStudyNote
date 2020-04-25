package yc.al.zuo.advanced;

/**
 * 进阶班第二课————英语表达式
 *
 * @author code_yc
 * @version 1.0
 * @date 2020/4/21 17:23
 */
public class Class2_Code3_EnglishExpression {

    // 主函数
    public static String getNumEngExp(int num) {
        if (num == 0) {
            return "Zero";
        }
        String res = "";
        // 如果num小于0，先加上负号
        if (num < 0) {
            res += "Negative, ";
        }
        // num如果为最小值时，单独考虑，否则abs之后就会出现溢出的现象
        if (num == Integer.MIN_VALUE) {
            res += "Two Billion, ";
            num %= -2000000000;
        }
        num = Math.abs(num);
        int height = 1000000000;  // 最高从十亿开始计算
        int heightIndex = 0;
        String[] names = {"Billion", "Million", "Thousand", ""};   // 分别存放十亿，百万，和千
        while (num != 0) {
            int cur = num / height;
            num %= height;
            if (cur != 0) {
                res += num1To999(cur);
                res += names[heightIndex] + (num == 0 ? " " : ", ");
            }
            height /= 1000;   // height下调三位
            heightIndex++;
        }
        return res;
    }

    // 计算1-999的树的字符串表达式
    private static String num1To999(int num) {
        if (num < 1 || num > 999) {
            return "";
        }
        if (num < 100) {
            return num1To99(num);
        }
        int high = num / 100;
        return num1To19(high) + "Hundred " + num1To99(num % 100);
    }

    // 计算1-99之间的计算结果
    private static String num1To99(int num) {
        if (num < 1 || num > 99) {
            return "";
        }
        if (num < 20) {
            return num1To19(num);
        }
        int high = num / 10;
        String[] names = {"Twenty ", "Thirty ", "Forty ", "Fifty ",
                "Sixty ", "Seventy ", "Eighty ", "Ninety "};
        return names[high - 2] + num1To19(num % 10);
    }

    // 计算1到19
    private static String num1To19(int num) {
        if (num < 1 || num > 19) {
            return "";
        }
        String[] names = {"One ", "Two ", "Three ", "Four ", "Five ", "Six ",
                "Seven ", "Eight ", "Nine ", "Ten ", "Eleven ", "Twelve ",
                "Thirteen ", "Fourteen ", "Fifteen ", "Sixteen ", "Sixteen ",
                "Eighteen ", "Nineteen "};
        return names[num - 1];
    }

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // ++                           测试集                                ++
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    // 生成一个随机数
    public static int generateRandomNum() {
        boolean isNeg = Math.random() > 0.5 ? false : true;
        int value = (int) (Math.random() * Integer.MIN_VALUE);
        return isNeg ? value : -value;
    }

    public static void main(String[] args) {
        System.out.println(getNumEngExp(0));
        System.out.println(getNumEngExp(Integer.MAX_VALUE));
        System.out.println(getNumEngExp(Integer.MIN_VALUE));
        int num = generateRandomNum();
        System.out.println(num);
        System.out.println(getNumEngExp(num));
    }
}
