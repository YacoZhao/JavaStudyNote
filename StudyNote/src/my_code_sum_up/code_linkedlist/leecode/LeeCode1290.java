package my_code_sum_up.code_linkedlist.leecode;

import my_code_sum_up.code_linkedlist.util.ListNode;

//1290. 二进制链表转整数
public class LeeCode1290 {

    public int getDecimalValue(ListNode head) {
        // 首先遍历走一遍，查看此链表的长度
        if (head == null) return 0;
        int l = 0;
        ListNode c = head;
        while (c != null) {
            l++;
            c = c.next;
        }
        int res = 0;
        c = head;
        while (c != null) {
            res += c.val == 1 ? 2 << (--l) : 0 * (--l);
            c = c.next;
        }
        return res;
    }
}
