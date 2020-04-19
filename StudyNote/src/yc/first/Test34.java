package yc.first;

//34. 在排序数组中查找元素的第一个和最后一个位置
public class Test34 {
    //两边二分查找算法
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        //1. 进行边界条件判断
        if (n == 0) {
            return new int[]{-1, -1};
        }
        if (n == 1) {
            return nums[0] == target ? new int[]{0, 0} : new int[]{-1, -1};
        }
        //第一步： 查找数组中第一个为target的元素下标
        int left = 0;
        int right = n - 1;
        int first_c = -1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        first_c = target == nums[left] ? left : -1;
        if (first_c == -1) {
            return new int[]{-1, -1};
        }

        //第二步，查找数组中最后一个为target的元素的下标
        int second_c = first_c;
        left = 0;
        right = n - 1;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        second_c = left;
        return new int[]{first_c, second_c};
    }
}
