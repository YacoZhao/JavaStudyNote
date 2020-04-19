package my_code_sum_up.code_sort;

import java.util.*;

public class Test {

    //快排
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        // 遍历结束之后来一个二分查找即可
        return nums[nums.length / 2];
    }
}
