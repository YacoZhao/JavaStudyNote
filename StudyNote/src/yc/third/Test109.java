package yc.third;

import my_code_sum_up.code_linkedlist.util.ListNode;
import yc.second.TreeNode;

public class Test109 {

    /**
     * 递归求解
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        //边界判断
        if(head == null){
            return null;
        }
        if(head.next == null){
            return new TreeNode(head.val);
        }
        //遍历出链表的长度
        int len = 0;
        ListNode temp = head;
        while (temp != null){
            len++;
            temp = temp.next;
        }
        //找出中点的节点构成数的根节点
        ListNode mid = head;
        for (int i = 1; i < len / 2; i++) {
            mid = mid.next;
        }
        ListNode right_link = mid.next.next;
        TreeNode res = new TreeNode(mid.next.val);
        mid.next = null;
        res.left = sortedListToBST(head);  // 左子树尾左边链表递归的结果
        res.right = sortedListToBST(right_link); //右子树为右边链表递归的结果
        return res;
    }
}
