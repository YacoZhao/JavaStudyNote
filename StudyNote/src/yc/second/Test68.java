package yc.second;

import java.util.ArrayList;
import java.util.List;

public class Test68 {
    public List<String> fullJustify(String[] words, int maxWidth) {
        //1. 定义返回的结果集
        List<String> ans = new ArrayList<>();
        //2. 定义当前行取出来的字符串集合
        List<String> row = new ArrayList<>();
        //3. 定义当前获取到的字符串长度
        int curLen = 0;
        //4. 循环遍历words数组，取出字符串
        for (int i = 0; i < words.length; ) {
            //4.1 如果当前curLen加上获取的字符串长度小于maxWidth，则表示可以加入
            // 分为两种情况：第一次加入直接加入，第二次加入要留符号位
            if (curLen == 0 && curLen + words[i].length() <= maxWidth || curLen > 0 && curLen + 1 + words[i].length() <= maxWidth) {
                row.add(words[i]);
                if (curLen == 0) {
                    curLen = curLen + words[i].length();
                } else {
                    curLen = curLen + words[i].length() + 1;
                }
                i++;
            } else {   //4.2 如果当前加入超出maxWidth时，则加入不了了，对已经加入的行做调整
                //1. 定义存放当前字符串的结果集
                StringBuilder temp = new StringBuilder();
                //2. 获取row中的元素加入temp中
                temp.append(row.get(0));
                //3. 如果当前结果集中只有一个元素，则元素结尾补0
                if (row.size() == 1) {
                    temp.append(getStringBlank(maxWidth - curLen));
                    ans.add(temp.toString());
                }
                //4. 如果不止一个元素
                if (row.size() > 1) {
                    //1. 计算出一共剩余多少个空格
                    int blank = maxWidth - curLen + row.size() - 1;
                    //2. 均分空格
                    int even = blank / (row.size() - 1);
                    //3. 计算多出的空格数
                    int preEven = blank - (even * (row.size() - 1));
                    //4. 前preEven个元素加入时，需要多一个空格
                    int k = 1;
                    String s = getStringBlank(even + 1);
                    for (int j = 0; j < preEven; j++) {
                        temp.append(s + row.get(k));
                        k++;
                    }
                    //5. 将剩余的元素加上
                    s = getStringBlank(even);
                    for (; k < row.size(); k++) {
                        temp.append(s + row.get(k));
                    }
                    //6. 将结果加入结果集
                    ans.add(temp.toString());
                }
                //1. row和curLen至空
                row = new ArrayList<>();
                curLen = 0;
            }
        }
        //5. for循环之后，row中会存在一组没有加入到ans中的元素
        //5.1 按标准顺序加入数组
        StringBuilder temp = new StringBuilder();
        temp.append(row.get(0));
        for (int i = 1; i < row.size(); i++) {
            temp.append(" " + row.get(i));
        }
        //5.2 字符串末尾用空格补齐
        temp.append(getStringBlank(maxWidth - curLen));
        ans.add(temp.toString());
        //6. 返回结果
        return ans;
    }


    /**
     * 获取指定长度的空白字符串
     *
     * @param n
     * @return
     */
    private String getStringBlank(int n) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            res.append(" ");
        }
        return res.toString();
    }


    public List<String> fullJustify2(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int currentLen = 0;
        int start = 0;
        int end = 0;
        for (int i = 0; i < words.length;) {
            if (currentLen == 0 && currentLen + words[i].length() <= maxWidth || currentLen > 0 && currentLen + 1 + words[i].length() <= maxWidth) {
                end++;
                if (currentLen == 0) {
                    currentLen = currentLen + words[i].length();
                } else {
                    currentLen = currentLen + 1 + words[i].length();
                }
                i++;
            } else {
                int sub = maxWidth - currentLen + (end - start) - 1;
                if (end - start == 1) {
                    String blank = getStringBlank(sub);
                    ans.add(words[start] + blank);
                } else {
                    StringBuilder temp = new StringBuilder();
                    temp.append(words[start]);
                    int averageBlank = sub / ((end - start) - 1);
                    //如果除不尽，计算剩余空格数
                    int missing = sub - averageBlank * ((end - start) - 1);
                    String blank = getStringBlank(averageBlank + 1);
                    int k = 1;
                    for (int j = 0; j < missing; j++) {
                        temp.append(blank + words[start+k]);
                        k++;
                    }
                    blank = getStringBlank(averageBlank);
                    for (; k <(end - start); k++) {
                        temp.append(blank + words[start+k]);
                    }
                    ans.add(temp.toString());

                }
                start = end;
                currentLen = 0;

            }
        }
        StringBuilder temp = new StringBuilder();
        temp.append(words[start]);
        for (int i = 1; i < (end - start); i++) {
            temp.append(" " + words[start+i]);
        }
        temp.append(getStringBlank(maxWidth - currentLen));
        ans.add(temp.toString());
        return ans;
    }
}
