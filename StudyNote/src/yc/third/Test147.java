package yc.third;

import my_code_sum_up.code_linkedlist.util.ListNode;

public class Test147 {

    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = head;
        while(cur.next != null){
            if(cur.val > cur.next.val){
                ListNode change = cur.next;
                cur.next = cur.next.next;
                swap(dummy,change);
            }else{
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    private void swap(ListNode dummy, ListNode change) {
        ListNode node = dummy;
        while(node.next.val < change.val){
            node = node.next;
        }
        ListNode next = node.next;
        node.next = change;
        change.next = next;
    }
}
