package my_code_sum_up.code_linkedlist.leecode;

import my_code_sum_up.code_linkedlist.util.ListNode;

//23. 合并K个排序链表
public class LeeCode23 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) return null;
        if (lists.length == 1) return lists[0];

        return process(lists, 0, lists.length - 1);
    }

    private ListNode process(ListNode[] lists, int l, int r) {
        if (l > r) return null;
        if (l == r) {
            return lists[l];
        }
        int mid = l + ((r - l) >> 1);
        ListNode left = process(lists, l, mid);
        ListNode right = process(lists, mid + 1, r);
        return merge(left, right);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return dummy.next;
    }
}
