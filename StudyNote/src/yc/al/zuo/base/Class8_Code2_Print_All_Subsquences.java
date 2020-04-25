package yc.al.zuo.base;

/**
 * 打印一个字符串的全部子序列
 * @author code_yc
 * @version 1.0
 * @date 2020/4/25 8:38
 */
public class Class8_Code2_Print_All_Subsquences {
    /**
     * 方法体：打印一个字符串的全部子序列
     * @param str  待打印的字符串
     */
    public static void printSubElement(String str) {
        printMethod(str.toCharArray(),0);
    }

    // 打印递归体
    public static void printMethod(char[] chars, int i) {
        if(i == chars.length) {
            System.out.println(String.valueOf(chars));
            return;
        }
        // 当前位置的元素考虑（递归进入下一层）
        printMethod(chars,i + 1);
        // 不考虑当前层，递归进入下一层
        char temp = chars[i];
        chars[i] = 32;
        printMethod(chars, i + 1);
        // 递归调用之后，将原来取出来的temp再放回去
        chars[i] = temp;
    }

    // 3.打印一个字符串的全部子序列(自己手撸改进算法)
    public static void printSubElement2 (String str) {
        process(str,0,"");
    }

    /**
     * 递归体
     * @param str
     * @param i
     */
    private static void process(String str, int i,String res) {
        if(i == str.length()){
            System.out.println(res);
            return;
        }
        process(str,i+1,res);
        process(str,i+1,res + str.charAt(i));
    }


    // 主函数：测试函数
    public static void main(String[] args) {
        String test = "abc";
        printSubElement2(test);
    }
}
