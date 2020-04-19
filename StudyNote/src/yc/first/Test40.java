package yc.first;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Test40 {
    //定义一个最终的结果
    List<List<Integer>> res = new ArrayList<>();
    int len;   //表示数组的长度
    int[] candidates;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //首先进行特殊条件判断
        if(candidates.length == 0){
            return res;
        }
        //将数组进行排序
        Arrays.sort(candidates);
        this.len = candidates.length;
        this.candidates = candidates;
        //调用回溯算法主体
        backtrack(target,0,new Stack<>());
        return res;
    }

    /**
     * 回溯
     * @param residue 当前的计算结果
     * @param start   选择列表的其实索引
     * @param track   路径容器
     */
    public void backtrack(int residue, int start, Stack<Integer> track){
        //首先进行边界条件判断
        if(residue == 0){
            res.add(new ArrayList<>(track));
            return;
        }

        //循环从选择列表中选出参数
        for (int i = start; i < len && residue - candidates[i] >= 0; i++) {
            //添加路径
            track.push(candidates[i]);
            //下一层
            backtrack(residue-candidates[i],i+1,track);
            //回溯
            track.pop();
        }
    }

    public static void main(String[] args) {
        Test40 t = new Test40();
        int[] arr = {10,1,2,7,6,1,5};
        System.out.println(t.combinationSum(arr,8));
    }
}
