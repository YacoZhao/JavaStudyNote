package yc.first;

//26. 删除排序数组中的重复项
public class Test26 {
    //双指针解法
    /*public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i;
    }*/

    //二刷————双指针
    public int removeDuplicates(int[] nums){
        int slow = 0;
        for (int i = 0; i < nums.length; i++) {
            if(i == 0 || (nums[i] != nums[i - 1])){
                nums[slow++] = nums[i];
            }
        }
        return slow;
    }
}
