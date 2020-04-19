package yc.first;

//28. 实现 strStr()
public class Test28 {

    //循环遍历两个字符串
    public int strStr(String haystack, String needle) {
        int res = -1;
        if (needle.length() == 0) {
            return 0;
        }
        char[] chars1 = haystack.toCharArray();
        char[] chars2 = needle.toCharArray();
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] == chars2[0]) {
                if (i + chars2.length > chars1.length) {
                    continue;
                }
                for (int j = 0; j < chars2.length; j++) {
                    res = i;
                    if (chars1[i + j] != chars2[j]) {
                        res = -1;
                        break;
                    }
                }
                if (res > -1) {
                    return res;
                }
            }
        }
        return res;
    }
}
