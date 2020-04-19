package my_code_sum_up.code_linkedlist.leecode;

import my_code_sum_up.code_linkedlist.util.ListNode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//817. 链表组件
public class LeeCode817 {


    public int numComponents(ListNode head, int[] G) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i : G) {
            set.add(i);
        }
        ListNode cur = head;
        int ans = 0;
        while (cur != null) {
            if (set.contains(cur.val) && (cur.next == null || !set.contains(cur.next.val))) {
                ans++;
            }
            cur = cur.next;
        }
        return ans;
    }
}
