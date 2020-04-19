package my_code_sum_up.code_linkedlist.leecode;

import my_code_sum_up.code_linkedlist.util.ListNode;

public class LeeCode83 {

    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode cur = head;
        ListNode f = head;

        while(cur != null){
            if(cur.next != null && cur.val == cur.next.val) {
                ListNode temp = cur.next;
                while (temp != null && cur.val == temp.val) {
                    temp = temp.next;
                }
                cur.next = temp;
            }else{
                cur = cur.next;
            }
        }
        return head;
    }
}
