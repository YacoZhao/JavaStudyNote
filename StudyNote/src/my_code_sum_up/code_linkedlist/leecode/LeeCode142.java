package my_code_sum_up.code_linkedlist.leecode;

import my_code_sum_up.code_linkedlist.util.ListNode;

// 返回第一个入环节点
public class LeeCode142 {

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return null;

        // 快慢指针搜索
        ListNode s = head.next;
        ListNode f = head.next.next;
        while (s != f) {
            if (f.next == null || f.next.next == null) {
                return null;
            }
            s = s.next;
            f = f.next.next;
        }
        f = head;
        while(s != f){
            s = s.next;
            f = f.next;
        }
        return s;
    }
}
