package yc.second;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Test99 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(9);
        root.left.left = new TreeNode(-2147483648);
        root.left.right = new TreeNode(2);
        recoverTree(root);
        System.out.println(root.val);
    }

    static int i = 0;

    /**
     * @param root
     */
    public static void recoverTree(TreeNode root) {
        //首先通过中序遍历获得树的列表
        List<Integer> res = new ArrayList<>();
        infixOrder(root, res);
        res.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1 < o2){
                    return -1;
                }else if(o1 > o2){
                    return 1;
                }else{
                    return 0;
                }
            }
        });
        changeTree(root, res);
    }

    /**
     * 根据排序的结果跟新树
     *
     * @param root
     * @param list
     * @param
     */
    private static void changeTree(TreeNode root, List<Integer> list) {
        if (root != null) {
            changeTree(root.left, list);
            root.val = list.get(i++);
            changeTree(root.right, list);
        }
    }

    /**
     * 中序遍历出数组
     * @param root
     * @param ans
     */
    private static void infixOrder(TreeNode root, List<Integer> ans) {
        if (root != null) {
            infixOrder(root.left, ans);
            ans.add(root.val);
            infixOrder(root.right, ans);
        }
    }

}
