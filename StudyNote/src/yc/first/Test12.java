package yc.first;

//12. 整数转罗马数字(中等)
public class Test12 {

    //方法一：贪心算法
    public String intToRoman(int num) {
        String[] romans = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder ans = new StringBuilder();

        int index = 0;
        while (index < romans.length && num > 0) {
            while (num >= values[index]) {
                ans.append(romans[index]);
                num = num - values[index];
            }
            index++;
        }
        return ans.toString();
    }
}
