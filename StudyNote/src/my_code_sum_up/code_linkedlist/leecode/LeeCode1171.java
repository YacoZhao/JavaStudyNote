package my_code_sum_up.code_linkedlist.leecode;

import my_code_sum_up.code_linkedlist.util.ListNode;

//1171. 从链表中删去总和值为零的连续节点

/**
 * 递归思想：（手撸 ）
 *  从头结点向后开始加，如果累加和为0，则删除节点
 *
 *  如果没有，删除下面的节点
 *
 */
public class LeeCode1171 {

    public ListNode removeZeroSumSublists(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode res = process(head);
        if (res == head) {
            head.next = removeZeroSumSublists(head.next);
        } else {
            head = res;
            dummy.next = removeZeroSumSublists(head);
        }
        return dummy.next;
    }

    public ListNode process(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = head;
        int sum = 0;
        while (cur != null) {
            sum += cur.val;
            if (sum == 0) {
                dummy.next = cur.next;
                break;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}
