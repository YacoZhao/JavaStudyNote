package yc.second;

import java.util.ArrayList;
import java.util.List;

public class Test95 {

    /**
     * 解法一： 递归的思想
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return getAns(1, n);
    }

    /**
     * 递归体
     *
     * @param start
     * @param end
     * @return
     */
    private List<TreeNode> getAns(int start, int end) {
        //创建返回的结果集
        List<TreeNode> res = new ArrayList<>();
        //如果start > end, 没有想要的结果，返回空结果集
        if (start > end) {
            res.add(null);  //一定要把null加入
            return res;
        }
        //如果start == end ，只有一个元素， 构成一个数
        if (start == end) {
            res.add(new TreeNode(start));
            return res;
        }
        //循环遍历从start到end
        for (int i = start; i <= end; i++) {
            //得到所有的左子树列表
            List<TreeNode> lefts = getAns(start, i - 1);
            //得到所有的右子树列表
            List<TreeNode> rights = getAns(i + 1, end);
            //左右子树两两结合
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    //加入结果集
                    res.add(root);
                }
            }
        }
        //返回结果
        return res;
    }
}
