package yc.first;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//46.全排列

/**
 * 回溯算法求解全排列
 */
public class Test46 {
    List<List<Integer>> res = new ArrayList<>();
    int[] nums;

    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) {
            return res;
        }
        this.nums = nums;
        List<Integer> track = new LinkedList<>();
        backtrack(nums, track);
        return res;
    }

    /**
     * 回溯
     *
     * @param nums  选择列表
     * @param track 路径存放容器
     */
    private void backtrack(int[] nums, List<Integer> track) {
        //结束条件
        if (track.size() == nums.length) {
            res.add(new ArrayList<>(track));
            return;
        }
        //遍历选择列表
        for (int i = 0; i < nums.length; i++) {
            //如果已经包含了，则遍历下一个
            if (track.contains(nums[i])) {
                continue;
            }
            //选择
            track.add(nums[i]);
            //回溯
            backtrack(nums, track);
            //撤销选择
            track.remove(track.size() - 1);
        }
    }
}
