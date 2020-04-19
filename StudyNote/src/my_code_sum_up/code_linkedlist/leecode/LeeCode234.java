package my_code_sum_up.code_linkedlist.leecode;

import my_code_sum_up.code_linkedlist.util.ListNode;

//234. 回文链表
public class LeeCode234 {

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        // 快慢指针走到到中间节点
        ListNode n1 = head;
        ListNode n2 = head;
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }
        n2 = n1.next;
        n1.next = null;
        // 反转链表
        ListNode n3 = null;
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        n3 = n1;
        boolean res = true;
        n2 = head;
        while (n1 != null && n2 != null) {
            if (n1.val != n2.val) {
                res = false;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        while (n3 != null) {
            n2 = n3.next;
            n3.next = n1;
            n1 = n3;
            n3 = n2;
        }
        return res;
    }
}
