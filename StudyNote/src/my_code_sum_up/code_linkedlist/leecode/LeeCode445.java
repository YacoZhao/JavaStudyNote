package my_code_sum_up.code_linkedlist.leecode;

import my_code_sum_up.code_linkedlist.util.ListNode;

import javax.jnlp.ClipboardService;
import java.util.Stack;

//445. 两数相加 II(经典好题)
public class LeeCode445 {
    int carry = 0;

    // 解法一：递归
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        // 找出两个链表的长度
        int n1 = 0, n2 = 0;
        ListNode c1 = l1;
        ListNode c2 = l2;
        while (c1 != null) {
            n1++;
            c1 = c1.next;
        }
        while (c2 != null) {
            n2++;
            c2 = c2.next;
        }
        ListNode res = process(l1, l2, n1, n2);
        if (carry != 0) {
            ListNode newHead = new ListNode(1);
            newHead.next = res;
            res = newHead;
        }
        return res;
    }

    private ListNode process(ListNode l1, ListNode l2, int n1, int n2) {
        int temp;
        if (n1 == 1 && n2 == 1) {
            temp = l1.val;
            l1.val = (temp + l2.val + carry) % 10;
            carry = (temp + l2.val + carry) / 10;
            return l1;
        }
        if (n1 == n2) {
            temp = l1.val;
            l1.next = process(l1.next, l2.next, n1 - 1, n2 - 1);
            l1.val = (temp + l2.val + carry) % 10;
            carry = (temp + l2.val + carry) / 10;
            return l1;
        }
        if (n1 < n2) {
            temp = l2.val;
            l2.next = process(l1, l2.next, n1, n2 - 1);
            l2.val = (carry + temp) % 10;
            carry = (carry + temp) / 10;
            return l2;
        }
        temp = l1.val;
        l1.next = process(l1.next, l2, n1 - 1, n2);
        l1.val = (carry + temp) % 10;
        carry = (carry + temp) / 10;
        return l1;
    }

    //解法二：双栈实现
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        Stack<ListNode> s1 = new Stack<ListNode>();
        Stack<ListNode> s2 = new Stack<ListNode>();
        ListNode c1 = l1, c2 = l2;
        while (c1 != null) {
            s1.push(c1);
            c1 = c1.next;
        }
        while (c2 != null) {
            s2.push(c2);
            c2 = c2.next;
        }
        int sum = 0;
        int v = 0;
        int carry = 0;
        c1 = new ListNode(-1);
        while (!s1.isEmpty() && !s2.isEmpty()) {
            sum = s1.pop().val + s2.pop().val + carry;
            v = sum % 10;
            carry = sum / 10;
            c2 = c1.next;
            c1.next = new ListNode(v);
            c1.next.next = c2;
        }
        s1 = s1.isEmpty() ? s2 : s1;
        while (!s1.isEmpty()) {
            sum = s1.pop().val + carry;
            v = sum % 10;
            carry = sum / 10;
            c2 = c1.next;
            c1.next = new ListNode(v);
            c1.next.next = c2;
        }
        if(carry != 0){
            c2 = c1.next;
            c1.next = new ListNode(1);
            c1.next.next = c2;
        }
        return c1.next;
    }
}
