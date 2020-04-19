package yc.first;

import java.util.*;

//全排列二
public class Test47 {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        int len = nums.length;
        Deque<Integer> track = new ArrayDeque<>();
        boolean[] use = new boolean[len];

        backtrack(nums, track, len, use, 0);
        return res;
    }

    /**
     * 回溯
     *
     * @param nums  选择列表
     * @param track 路径存放容器
     */
    private void backtrack(int[] nums, Deque<Integer> track, int len, boolean[] use, int depth) {
        //结束条件
        if (depth == len) {
            res.add(new ArrayList<>(track));
            return;
        }
        //遍历
        for (int i = 0; i < len; i++) {
            if (!use[i]) {
                if (i > 0 && nums[i] == nums[i - 1] && !use[i - 1]) {
                    continue;
                }
                //选择
                use[i] = true;
                track.addLast(nums[i]);
                //下一层
                backtrack(nums, track, len, use, depth + 1);
                //回溯
                track.removeLast();
                use[i] = false;
            }
        }
    }
}
