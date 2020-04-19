package yc.first;

//外观数列
public class Test38 {
    public String countAndSay(int n) {
        String s = "1";
        for (int i = 1; i < n; i++) {
            StringBuilder str = new StringBuilder();
            char pre = s.charAt(0);
            int count = 1;
            for (int j = 1; j < s.length(); j++) {
                char ch = s.charAt(j);
                if (pre == ch) {
                    count++;
                } else {
                    str.append(count).append(pre);
                    pre = ch;
                    count = 1;
                }
            }
            str.append(count).append(pre);
            s = str.toString();
        }
        return s;
    }
}
