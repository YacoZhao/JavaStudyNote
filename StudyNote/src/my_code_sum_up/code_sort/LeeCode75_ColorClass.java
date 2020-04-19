package my_code_sum_up.code_sort;

/**
 * 颜色分类问题：
 *
 *      快排的基本思想： 荷兰国旗问题
 */
public class LeeCode75_ColorClass {

    public void sortColors(int[] nums) {
        if(nums == null || nums.length < 2) return;
        process(nums,0,nums.length - 1);
    }

    private void process(int[] nums, int l, int r) {
        int less = l - 1;
        int more = r + 1;
        while(l < more){
            if(nums[l] < 1){
                swap(nums,++less,l++);
            }else if(nums[l] > 1) {
                swap(nums,--more,l);
            }else{
                l++;
            }
        }
    }

    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
