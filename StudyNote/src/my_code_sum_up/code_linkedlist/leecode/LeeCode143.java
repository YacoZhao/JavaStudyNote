package my_code_sum_up.code_linkedlist.leecode;

import my_code_sum_up.code_linkedlist.util.ListNode;

//143. 重排链表
public class LeeCode143 {
    public void reorderList(ListNode head) {
        // 首先边界条件
        if(head == null || head.next == null) return;
        // 快慢指针走到中间节点
        ListNode s = head;
        ListNode f = head;
        while(f.next != null && f.next.next != null){
            s = s.next;
            f = f.next.next;
        }
        f = s.next;
        s.next = null;

        // 将f链表反转
        s = reverseList(f);
        f = head;
        ListNode next_s = null;
        ListNode next_f = null;
        while(f != null && s!= null){
            next_f = f.next;
            next_s = s.next;
            s.next = null;
            f.next = s;
            s.next = next_f;
            f = next_f;
            s = next_s;
        }
    }

    // 反转单链表
    private ListNode reverseList(ListNode f) {
        if(f == null || f.next == null) return f;
        ListNode pre = null;
        ListNode next = null;
        while(f != null){
            next = f.next;
            f.next = pre;
            pre = f;
            f = next;
        }
        return pre;
    }

}
