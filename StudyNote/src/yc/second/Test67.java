package yc.second;

public class Test67 {
    /**
     * 官方写法
     * @param a
     * @param b
     * @return
     */
    public String addBinary2(String a, String b) {
        StringBuilder builder = new StringBuilder();
        int sum = 0;
        for(int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0 ; i--, j--){
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            builder.append(sum % 2);
            sum = sum / 2;
        }
        builder.append(sum == 1 ? 1 : "");
        return builder.reverse().toString();
    }
}
