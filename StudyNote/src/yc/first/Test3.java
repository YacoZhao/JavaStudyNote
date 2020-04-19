package yc.first;

import java.util.HashMap;
import java.util.HashSet;

/**
 * （中等）
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 *
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 *
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Test3 {

    //方法一：暴力穷举(超时)
    public static int lengthOfLongestSubstring(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length() + 1; j++) {
                if (isLongStr(s, i, j)) {
                    ans = Math.max(ans, j - i);
                }
            }
        }
        return ans;
    }

    //判断是否为无重复字符串
    public static boolean isLongStr(String s, int start, int end) {
        HashSet<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            if (set.contains(s.charAt(i))) {
                return false;
            }
            set.add(s.charAt(i));
        }
        return true;
    }

    //方法二：滑动窗口，利用Set集合遍历
    public static int lengthOfLongestSubstring2(String s) {
        HashSet<Character> set = new HashSet<>();
        int i = 0, j = 0, ans = 0;
        int n = s.length();
        while (i < n && j < n) {
            char ch = s.charAt(j);
            if (!set.contains(ch)) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                //set.remove(ch);  //这里是错误的，假设连续出现了5个C，这样做的话一直在删除C，而根本没有动前面的值
                //i++;
                set.remove(s.charAt(i++));   //前面的全删除，直到删除了ch才结束
            }
        }
        return ans;
    }

    //方法三：使用HashMap优化滑动窗口（如果碰到了重复的元素，则跳到下一元素重新搜索，遍历j）
    public static int lengthOfLongestSubstring3(String s) {
        int ans = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int i = 0;  //遍历的初始指针
        for (int j = 0; j < s.length(); j++) {
            char ch = s.charAt(j);
            if (map.containsKey(ch)) {
                i = Math.max(map.get(ch), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(ch, j + 1);
        }
        return ans;
    }

    //方法四，直接使用字符索引数组优化滑动窗口
    public static int lengthOfLongestSubstring4(String s) {
        int ans = 0;
        int[] index = new int[128];
        int len = s.length();
        for (int j = 0, i = 0; j < len; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }
}
