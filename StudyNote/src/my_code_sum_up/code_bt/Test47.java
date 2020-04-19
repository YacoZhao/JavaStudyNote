package my_code_sum_up.code_bt;

import java.util.*;

// 全排列二
// 方法：回溯
public class Test47 {

    // -----------------------回溯-----------------------------------------
    // ---------使用dp标志速度比直接判断判断path是否已经存在的速度快-------------
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        Deque<Integer> path = new ArrayDeque<Integer>();
        boolean[] dp = new boolean[nums.length];
        dfs(ans,nums,path,dp);
        return ans;
    }

    private void dfs(List<List<Integer>> ans, int[] nums, Deque<Integer> path,boolean[] dp){
        if(path.size() == nums.length){
            ans.add(new ArrayList<>(path));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            //-------------修改的地方-------------
            if(i > 0 && nums[i] == nums[i - 1] && !dp[i - 1]) {
                continue;
            }
            //-------------修改的地方----------------------
            if(!dp[i]){
                dp[i] = true;
                path.addLast(nums[i]);
                dfs(ans,nums,path,dp);
                dp[i] = false;
                path.removeLast();
            }
        }
    }
}
