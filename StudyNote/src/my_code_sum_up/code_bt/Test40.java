package my_code_sum_up.code_bt;

import java.util.*;

// 40.数组总和Ⅱ
// 方法一：回溯算法
public class Test40 {

    //--------------------------------------回溯算法： 注意重复元素的剪枝------------------------------------------------
    //--------------------------------------直接修改39题代码-----------------------------------------------------
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
            //---------------------修改的地方：这里必须考虑重复，入过之前已经考虑过了相同的元素值，则直接跳过-----------------------------------
            // 如果当前的元素之前已经用过，那么不用
            if(i > s && candidates[i] == candidates[i - 1]){
                continue;
            }
            //---------------------------------------------------------
            // path添加当前元素
            path.addLast(candidates[i]);
            // 追溯（追溯后，遍历数组的起始位置要+1，保证一个位置的数只能用1次）
            dfs(ans,candidates,target - candidates[i],path,i+1);
            // 回溯
            path.removeLast();
        }
    }
}
