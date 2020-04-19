package my_code_sum_up.code_linkedlist.leecode;

import my_code_sum_up.code_linkedlist.util.ListNode;

/**
 *
 *  链表相加
 */
public class LeeCode02_TwoNumSum {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2){
        ListNode res = new ListNode(-1);
        ListNode cur = res;
        int carry = 0;  // 表示进位
        ListNode p1 = l1;
        ListNode p2 = l2;
        while(p1 != null || p2 != null){
            int v1 = p1 == null ? 0 : p1.val;
            int v2 = p2 == null ? 0 : p2.val;
            int sum = (v1 + v2 + carry) % 10;
            carry = (v1 + v2) / 10;
            cur.next = new ListNode(sum);
            cur = cur.next;
            p1 = p1 == null ? p1 : p1.next;
            p2 = p2 == null ? p2 : p2.next;
        }
        if(carry != 1){
            cur.next = new ListNode(1);
        }
        return res.next;
    }
}
