package my_code_sum_up.code_tree.leecode;

import my_code_sum_up.code_tree.util.TreeNode;

// 108. 将有序数组转换为二叉搜索树
public class LeeCode108 {

    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length == 0) return null;
        return process(nums,0,nums.length-1);
    }

    private TreeNode process(int[] nums, int left, int right){
        if(left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = process(nums,left,mid - 1);
        root.right = process(nums,mid+1,right);
        return root;
    }
}
