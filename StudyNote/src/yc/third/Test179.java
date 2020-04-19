package yc.third;

import java.util.Arrays;
import java.util.Comparator;

public class Test179 {

    public String largestNumber(int[] nums) {
        if(nums.length == 1) return nums[0] + "";
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = nums[i] + "";
        }
        Arrays.sort(strs,new MyComparator());
        String res = "";
        for (String str : strs) {
            res += str;
        }
        return res;
    }

    public class MyComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            String order1 = o1 + o2;
            String order2 = o2 + o1;
            return order1.compareTo(order2);
        }
    }
}
