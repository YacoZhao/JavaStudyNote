package my_code_sum_up.code_linkedlist.leecode;

import my_code_sum_up.code_linkedlist.util.ListNode;

import java.util.Stack;

public class LeeCode82 {

    // 删除重复节点
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode s = dummy;
        ListNode f = head;

        while(f != null){
            if(f.next != null && f.next.val == f.val){
                int v = f.val;
                while(f != null && f.val == v){
                    f = f.next;
                }
                if(f == null){
                    s.next = f;
                    break;
                }
            }else{
                s.next = f;
                f = f.next;
                s = s.next;
            }
        }
        return dummy.next;
    }
}
