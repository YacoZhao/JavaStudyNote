package my_code_sum_up.code_linkedlist.leecode;

import my_code_sum_up.code_linkedlist.util.LinkedUtils;
import my_code_sum_up.code_linkedlist.util.ListNode;

//92. 反转链表 II
public class LeeCode92 {

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if(m == n) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        // 首先找到节点待反转的位置
        for (int i = 0; i < m - 1; i++) {
            cur = cur.next;
        }
        ListNode newH = cur.next;  // 保存下一个待反转的节点
        ListNode end = newH;
        ListNode next = newH.next;
        newH.next = null;
        ListNode last = null;
        for (int i = 0; i < n - m; i++) {
            last = next.next;
            next.next = newH;
            newH = next;
            next = last;
        }
        cur.next = newH;
        end.next = next;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);

        LinkedUtils.printLinkedList(head);

        LinkedUtils.printLinkedList(reverseBetween(head,2,4));
    }
}
