package yc.al.zuo.advanced;

/**
 * 字符串匹配问题
 */
public class Class6_Code3_RegularExpressionMatch {

    public static boolean isMatch(String str, String exp) {
        if(str == null || exp == null) return false;
        if("".equals(exp)) return "".equals(str);
        return getAns(str.toCharArray(),exp.toCharArray(),0,0);
    }

    private static boolean getAns(char[] chs, char[] che, int s, int e) {
        return false;
    }

}
