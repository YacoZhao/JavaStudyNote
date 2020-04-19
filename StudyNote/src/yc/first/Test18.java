package yc.first;

import com.sun.corba.se.impl.ior.iiop.ORBTypeComponentImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//18. 四数之和
public class Test18 {
/*    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return ans;
        }
        Arrays.sort(nums);
        int length = nums.length;
        for (int c1 = 0; c1 < length - 3; c1++) {
            if (c1 > 0 && nums[c1] == nums[c1 - 1]) {
                continue;
            }
            int minSum1 = nums[c1] + nums[c1 + 1] + nums[c1 + 2] + nums[c1 + 3];
            if (minSum1 > target) {
                break;
            }
            int maxSum1 = nums[c1] + nums[length - 1] + nums[length - 2] + nums[length - 3];
            if (maxSum1 < target) {
                continue;
            }
            for (int c2 = c1 + 1; c2 < length - 2; c2++) {
                if (c2 > c1 + 1 && nums[c2] == nums[c2 - 1]) {
                    continue;
                }
                int minSum2 = nums[c1] + nums[c2] + nums[c2 + 1] + nums[c2 + 2];
                if (minSum2 > target) {
                    continue;
                }
                int maxSum2 = nums[c1] + nums[c2] + nums[length - 1] + nums[length - 2];
                if (maxSum2 < target) {
                    continue;
                }
                int c3 = c2 + 1;
                int c4 = length - 1;
                while (c3 < c4) {
                    int curSum = nums[c1] + nums[c2] + nums[c3] + nums[c4];
                    if (curSum == target) {
                        ans.add(Arrays.asList(nums[c1], nums[c2], nums[c3], nums[c4]));
                        c3++;
                        if (c3 < c4 && nums[c3] == nums[c3 - 1]) {
                            c3++;
                        }
                        c4--;
                        if (c3 < c4 && c2 < c4 && nums[c4] == nums[c4 + 1]) {
                            c4--;
                        }
                    } else if (curSum < target) {
                        c3++;
                    } else {
                        c4--;
                    }
                }
            }
        }
        return ans;
    }*/

    //二刷
    public List<List<Integer>> fourSum(int[] nums, int target){
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 4) {
            return res;
        }
        Arrays.sort(nums);
        int len = nums.length;
        //第一层循环
        for (int i = 0; i < len - 3; i++) {
            // 去重复操作
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 边界条件判断(左边界——最小值)
            int lower = nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3];
            if (lower > target) {
                break;
            }
            // 边界条件判断(右边界——最大值)
            int upper = nums[i] + nums[len - 1] + nums[len - 2] + nums[len - 3];
            if (upper < target) {
                continue;
            }
            //第二层循环
            for (int j = i + 1; j < len - 2; j++) {
                //去重操作
                if(j > i + 1 && nums[j] == nums[j - 1]){
                    continue;
                }
                // 左边界条件判断
                lower = nums[i] + nums[j] + nums[j + 1] + nums[j + 2];
                if (lower > target) {
                    break;
                }
                upper = nums[i] + nums[j] + nums[len - 1] + nums[len - 2];
                if (upper < target) {
                    continue;
                }
                //定义左右指针，进入第三层循环
                int left = j + 1;
                int right = len - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        right--;
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return res;
    }
}
