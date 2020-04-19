package yc.second;

public class Test100 {
    /**
     * 简单的递归
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        //1. 如果p和q都为空，返回true
        if(p == null && q == null){
            return true;
        }
        //2. 如果两者有一个为空，则返回false
        if(p == null || q == null){
            return false;
        }
        //3. 如果p的值和q的值不一样，则返回false
        if(p.val != q.val){
            return false;
        }
        //4.比较左数和右数
        return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
    }
}
