package yc.second;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Test78 {
    static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> subsets(int[] nums) {
        //边界条件判断
        if (nums.length == 0) {
            return res;
        }
        for (int k = 0; k <= nums.length; k++) {
            Stack<Integer> track = new Stack<>();
            backtrack(nums, 0, track, 1);
        }
        return res;
    }

    private static void backtrack(int[] nums, int begin, Stack<Integer> track, int k) {
        //结束条件
        if (track.size() == k) {
            res.add(new ArrayList<>(track));
            return;
        }
        //回溯
        for (int i = begin; i < nums.length; i++) {
            track.push(nums[i]);
            backtrack(nums, i + 1, track, k);
            track.pop();
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> list = subsets(nums);
        System.out.println(Arrays.toString(list.toArray()));
    }
}
