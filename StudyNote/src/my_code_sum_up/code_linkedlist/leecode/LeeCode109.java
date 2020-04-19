package my_code_sum_up.code_linkedlist.leecode;

import my_code_sum_up.code_linkedlist.util.ListNode;
import yc.second.TreeNode;

//109. 有序链表转换二叉搜索树
public class LeeCode109 {

    // 递归思想
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);

        //快慢指针找到中间节点
        ListNode s = head;
        ListNode f = head.next;
        while (f.next != null && f.next.next != null) {
            s = s.next;
            f = f.next.next;
        }
        f = s.next;
        s.next = null;
        ListNode r = f.next;
        TreeNode root = new TreeNode(f.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(r);
        return root;
    }
}
