package yc.third;

public class Test167 {
    /**
     * 解法一：二分查找的理想，先确定一个值，余下的值二分查找
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        //首先边界条件判断
        if(numbers.length < 2) return null;
        for (int i = 0; i < numbers.length - 1; i++) {
            if(numbers[i] > target) return null;
            int value = target - numbers[i];
            int left = i + 1;
            int right = numbers.length - 1;
            while(left <= right){
                int mid = left + (right - left) / 2;
                if(numbers[mid] == value){
                    return new int[]{i+1,mid+1};
                }else if(numbers[mid] > value){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }
        }
        return null;
    }

    /**
     * 解法二：双指针的解法
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum2(int[] numbers, int target){
        int left = 0;
        int right = numbers.length - 1;
        while(left < right){
            int sum = numbers[left] + numbers[right];
            if(sum == target){
                return new int[]{left + 1,right+1};
            }else if(sum > target){
                right--;
            }else{
                left++;
            }
        }
        return null;
    }
}
