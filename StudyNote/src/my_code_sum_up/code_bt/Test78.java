package my_code_sum_up.code_bt;

import java.util.*;

// 78. 子集
// 方法： 回溯
public class Test78 {

    //-------------------------方法一： 回溯-------------------------------------------
    // 返回不重复元素组成的数组的子集
    public List<List<Integer>> subsets(int[] nums){
        List<List<Integer>> ans = new ArrayList<>();
        if(nums.length > 0){
            Deque<Integer> path = new ArrayDeque<>();
            dfs(ans,nums,0,path);
        }
        return ans;
    }

    private void dfs(List<List<Integer>> ans, int[] nums, int s, Deque<Integer> path){
        // ---无论当前是什么结果，都加入ans----
        ans.add(new ArrayList<>(path));
        for (int i = s; i < nums.length; i++) {
            path.addLast(nums[i]);
            dfs(ans,nums,i+1,path);
            path.removeLast();
        }
    }

    //------------------------------方法二： 暴力递归-----------------------------------------
    public List<List<Integer>> subsets2(int[] nums) {
        return getAns(nums,0);
    }

    // 注意：这里一定要注意拷贝数组的细节，不可以直接在数组上做修改，必须先进行拷贝，然后再添加元素
    private List<List<Integer>> getAns(int[] nums, int s) {
        if(s == nums.length) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(new ArrayList<>());
            return ans;
        }
        List<List<Integer>> ans = new ArrayList<>();
        int cur = nums[s];
        List<List<Integer>> next = getAns(nums,s+1);
        for (List<Integer> list : next) {
            List<Integer> temp1 = new ArrayList<>(list);
            ans.add(temp1);
            List<Integer> temp2 = new ArrayList<>(list);
            temp2.add(0,cur);
            ans.add(temp2);
        }
        return ans;
    }

    // ------------------------------方法三： 普通迭代法(速度最快) -------------------------------------------
    public List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());   // 添加空的子集
        for (int num : nums) {
            List<List<Integer>> ans_temp = new ArrayList<>();
            for (List<Integer> list : ans) {
                List<Integer> temp = new ArrayList<>(list);
                temp.add(num);
                ans_temp.add(temp);
            }
            ans.addAll(ans_temp);
        }
        return ans;
    }
}
