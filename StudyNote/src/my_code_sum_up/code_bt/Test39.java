package my_code_sum_up.code_bt;

import java.util.*;


//39. 组合总和
public class Test39 {
    // ------------------------------方法一： 回溯算法--------------------------------------------------------
    // 回溯求解
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        // 将数组进行排序
        Arrays.sort(candidates);
        // 创建路径存储对象
        Deque<Integer> path = new ArrayDeque<Integer>();
        dfs(ans,candidates,target,path,0);
        return ans;
    }

    private void dfs(List<List<Integer>> ans, int[] candidates, int target, Deque<Integer> path,int s){
        // 结束条件
        if(target == 0){
            ans.add(new ArrayList<>(path));
            return;
        }
        // 如果targetr小于0le，不用继续执行，不满足
        if(target < 0){
            return;
        }
        for(int i = s; i < candidates.length; i++){
            // 如果当前的元素已经比剩余的target大，那么直接返回，因为数组已经排过序了
            if(candidates[i] > target){
                return;
            }
            // path添加当前元素
            path.addLast(candidates[i]);
            // 追溯
            dfs(ans,candidates,target - candidates[i],path,i);
            // 回溯
            path.removeLast();
        }
    }
}
