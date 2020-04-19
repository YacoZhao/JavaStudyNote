package yc.second;

//最后一个单词的长度
public class Test58 {
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int count = 0;
        char[] chars = s.toCharArray();
        int i = chars.length - 1;
        for (; i >= 0 ; i--) {
            if(chars[i] != ' '){
                break;
            }
        }
        for (; i >= 0 ; i--) {
            if(chars[i] != ' '){
                count++;
            }else{
                break;
            }
        }
        return count;
    }
}
