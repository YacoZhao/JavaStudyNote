package yc.second;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test90 {
    /**
     * 回溯算法
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, 0, new ArrayList<>(), ans);
        return ans;
    }

    private void backtrack(int[] nums, int start, ArrayList<Integer> track, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(track));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            track.add(nums[i]);
            backtrack(nums, i + 1, track, ans);
            track.remove(track.size() - 1);
        }
    }

}
