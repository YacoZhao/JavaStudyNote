package my_code_sum_up.code_sort;

import my_code_sum_up.code_linkedlist.util.ListNode;

/**
 *
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 *
 * 归并排序结合链表的使用
 *
 */
public class LeeCode148_SortLinkedList {

    //归并排序链表
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;

        //快慢指针将链表拆分成为左右两个链表
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = slow.next; // 代表右边的链表
        slow.next = null; // 代表左边的链表
        ListNode left = sortList(head);
        ListNode right = sortList(fast);

        //合并两个链表
        return merge(left,right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        if(left == null) return right;
        if(right == null) return left;
        ListNode cl = left;
        ListNode cr = right;
        if(cl.val < cr.val){
            cl.next = merge(left.next,right);
            return cl;
        }else{
            cr.next = merge(left,right.next);
            return cr;
        }
    }
}
