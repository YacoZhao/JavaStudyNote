package my_code_sum_up.code_linkedlist.leecode;

import my_code_sum_up.code_linkedlist.util.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LeeCode1019 {

    // 利用单调栈求解
    public int[] nextLargerNodes(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new int[]{0};
        //创建一个栈存放节点元素之
        Stack<Integer> s1 = new Stack<Integer>();
        ListNode cur = head;
        while (cur != null) {
            s1.push(cur.val);
            cur = cur.next;
        }
        int l = s1.size();
        int[] res = new int[l];
        Stack<Integer> s2 = new Stack<>();
        for (int i = l - 1; i >= 0; i--) {
            int v = s1.pop();
            if (s2.isEmpty()) {
                res[i] = 0;
            } else if (v >= s2.peek()) {
                while (!s2.isEmpty() && v >= s2.peek()) {
                    s2.pop();
                }
                res[i] = s2.isEmpty() ? 0 : s2.peek();
            } else {
                res[i] = s2.peek();
            }
            s2.push(v);
        }
        return res;
    }
}
