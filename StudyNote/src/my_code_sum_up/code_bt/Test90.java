package my_code_sum_up.code_bt;

import java.util.*;

//90. 子集2
public class Test90 {

    // -------------------------回溯法------------------------------------
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if(nums.length > 0){
            Arrays.sort(nums);
            boolean[] visited = new boolean[nums.length];
            Deque<Integer> path = new ArrayDeque<>();
            dfs(ans,nums,0,visited,path);
        }
        return ans;
    }

    private void dfs(List<List<Integer>> ans, int[] nums, int s, boolean[] visited, Deque<Integer> path) {
        ans.add(new ArrayList<>(path));
        for (int i = s; i < nums.length; i++) {
            if(visited[i]){
                continue;
            }
            if(i > s && nums[i] == nums[i - 1] && !visited[i - 1]){
                continue;
            }
            visited[i] = true;
            path.addLast(nums[i]);
            dfs(ans,nums,i+1,visited,path);
            visited[i] = false;
            path.removeLast();
        }
    }


    // -------------------------改进回溯法------------------------------------
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if(nums.length > 0){
            Arrays.sort(nums);
            Deque<Integer> path = new ArrayDeque<>();
            dfs2(ans,nums,0,path);
        }
        return ans;
    }

    private void dfs2(List<List<Integer>> ans, int[] nums, int s, Deque<Integer> path) {
        ans.add(new ArrayList<>(path));
        for (int i = s; i < nums.length; i++) {
            if(i > s && nums[i] == nums[i - 1]){
                continue;
            }
            path.addLast(nums[i]);
            dfs2(ans,nums,i+1,path);
            path.removeLast();
        }
    }
}
