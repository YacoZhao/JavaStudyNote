package my_code_sum_up.code_tree.leecode;

import my_code_sum_up.code_tree.util.TreeNode;

//110. 平衡二叉树
public class LeeCode110 {

    public class ResultData{
        boolean isAvl;
        int height;

        public ResultData(boolean isAvl, int height){
            this.isAvl = isAvl;
            this.height = height;
        }
    }

    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        return process(root).isAvl;
    }

    private ResultData process(TreeNode root){
        if(root == null){
            return new ResultData(true,0);
        }
        ResultData left = process(root.left);
        if(!left.isAvl){
            return new ResultData(false,0);
        }
        ResultData right = process(root.right);
        if(!right.isAvl){
            return new ResultData(false,0);
        }
        if(Math.abs(left.height - right.height) > 1){
            return new ResultData(false,0);
        }
        return new ResultData(true,Math.max(left.height,right.height) + 1);
    }
}
