package yc.first;

import my_code_sum_up.code_linkedlist.util.ListNode;

/**
 * （中等）
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Test2 {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    class Solution {

        //初等数学解法
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode head = new ListNode(0);
            int carry = 0;        //设置进位为0
            ListNode p1 = l1;
            ListNode p2 = l2;
            ListNode cur = head;
            while (p1 != null || p2 != null) {
                int x1 = p1 == null ? 0 : p1.val;
                int x2 = p2 == null ? 0 : p2.val;
                int sum = (x1 + x2 + carry) % 10;
                carry = (x1 + x2 + carry) / 10;
                cur.next = new ListNode(sum);
                if (p1 != null) {
                    p1 = p1.next;
                }
                if (p2 != null) {
                    p2 = p2.next;
                }
                cur = cur.next;
            }
            if (carry != 0) {
                cur.next = new ListNode(1);
            }
            return head.next;
        }
    }


    //二刷
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(0);
        ListNode p1 = l1;
        ListNode p2 = l2;
        int carry = 0;
        ListNode temp = dummy;
        while (p1 != null || p2 != null) {
            int x1 = p1 == null ? 0 : p1.val;
            int x2 = p2 == null ? 0 : p2.val;
            int sum = x1 + x2 + carry;
            carry = sum / 10;
            temp.next = new ListNode(sum % 10);
            temp = temp.next;
            if(p1 != null){
                p1 = p1.next;
            }
            if(p2 != null){
                p2 = p2.next;
            }
        }
        if (carry != 0) {
            temp.next = new ListNode(1);
        }
        return dummy.next;
    }
}



