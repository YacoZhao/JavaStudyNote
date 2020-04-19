package my_code_sum_up.code_linkedlist.leecode;

import my_code_sum_up.code_linkedlist.util.ListNode;

public class LeeCode141 {

    // 判断链表中是否有环
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return false;

        // 快慢指针搜索
        ListNode s = head.next;
        ListNode f = head.next.next;
        while (s != f) {
            if (f.next == null || f.next.next == null) {
                return false;
            }
            s = s.next;
            f = f.next.next;
        }
        return true;
    }
}
