package yc.first;

import java.util.*;

//15. 三数之和
public class Test15 {

    /*public List<List<Integer>> threeSum(int[] nums) {
        // 存放结果
        List<List<Integer>> ans = new ArrayList<>();
        // 边界判断（数组长度小于3，直接返回空集）
        if (nums == null || nums.length < 3) {
            return ans;
        }
        // 将数组按照升序排序，如果找出的第一个数就大于0，则直接break
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            // 如果第一个数大于0，后面的数肯定大于0，所以这里不可能会满足结果为0的条件
            if (nums[i] > 0) {
                break;
            }
            // 去重
            //如果前面数=当前数，那么当前数向后查找的可能性已经被前面的数搜索完，所以会出现重复，故此处需要去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 找出第一个点之后，对后面两个点进行双指针查找
            int left = i + 1;   //当前点的下一个位置
            int right = nums.length - 1;  //当前数据末尾
            // 因为此题要求left和right必须为两个独立的数，所以，left不可能等于right
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                // 三数之和 == 0
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 如果left指针下一位和当前位相同，右移去重
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // 如果right指针下一位和当前位相同，左移去重
                    while (right > left && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    // 找到一种结果集，则将左右指针都移动
                    left++;
                    right--;
                } else if (sum < 0) {
                    // 如果三数之和小于0，则要增大sum，所以left右移
                    left++;
                } else {
                    // 反之，right左移
                    right--;
                }
            }
        }
        return ans;
    }*/

    //二刷
    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }
}
