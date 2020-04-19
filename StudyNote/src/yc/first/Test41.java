package yc.first;

//41. 缺失的第一个正数
public class Test41 {

    //桶排序 + 抽屉原理
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;

        //将i元素，当如数组下标i-1的位置，前提i要满足数组的长度
        for (int i = 0; i < len; i++) {
            while (nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) {
                swap(nums, nums[i] - 1, i);
            }
        }

        //循环遍历改造过的数组，找到的第一个不对等下标的元素，即为要找的元素
        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return len + 1;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
