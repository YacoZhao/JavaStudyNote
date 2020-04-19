package yc.second;

public class Test80 {
    /**
     * 双指针(手撸)
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        //定义三个变量，left表示新数组已经更新的长度，right表示原数组遍历的长度，count计数器检查是否已经超出两个元素
        int left = 0, right = 0, count = 0;
        //右指针一直循环到数组尾
        while (right < nums.length) {
            if (right == 0 || nums[right] == nums[right - 1]) {
                //如果当前元素和上一个元素相等，或者为第一个元素
                //如果计数器还没有为2，计数器加一，右指针数据赋值为左指针
                if (count < 2) {
                    count++;
                    nums[left++] = nums[right];
                }
                //如果count已经为2的话，只动右指针，左指针不动
            } else {
                //如果当前元素和上一个元素不一样，右指针赋值给左指针，计数器置为1
                nums[left++] = nums[right];
                count = 1;
            }
            right++;
        }
        return left;
    }
}
