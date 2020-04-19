package yc.first;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 中等
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 *
 *
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 *
 * 示例 1:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 *
 *
 * 示例 2:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 *
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Test6 {

    //方法一： 按行排序
    public String convert1(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        ArrayList<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }
        char[] chars = s.toCharArray();
        boolean going = false;
        int curNums = 0;
        for (char ch : chars) {
            rows.get(curNums).append(ch);
            if (curNums == 0 || curNums == numRows - 1) {
                going = !going;
            }
            if (going) {
                curNums += 1;
            } else {
                curNums -= 1;
            }
        }
        StringBuilder ans = new StringBuilder();
        for (StringBuilder row : rows) {
            ans.append(row);
        }
        return ans.toString();
    }

    //方法一： 按行访问
    public String convert2(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder ans = new StringBuilder();
        int cycleLen = 2 * numRows - 2;
        char[] chars = s.toCharArray();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < chars.length; j = j + cycleLen) {
                ans.append(chars[i + j]);
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < chars.length) {
                    ans.append(chars[j + cycleLen - i]);
                }
            }
        }
        return ans.toString();
    }
}
