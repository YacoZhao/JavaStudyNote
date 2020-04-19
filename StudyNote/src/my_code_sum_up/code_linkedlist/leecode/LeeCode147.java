package my_code_sum_up.code_linkedlist.leecode;

import my_code_sum_up.code_linkedlist.util.ListNode;

//147. 对链表进行插入排序
public class LeeCode147 {

    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = head;
        while(cur.next != null){
            if(cur.val > cur.next.val){
                ListNode change = cur.next;
                cur.next = cur.next.next;
                insert(dummy,change);
            }else{
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    private void insert(ListNode dummy, ListNode change) {
        ListNode cur = dummy;
        int val = change.val;
        while(cur.next != null && cur.next.val < val){
            cur = cur.next;
        }
        ListNode next = cur.next;
        cur.next = change;
        change.next = next;
    }
}
