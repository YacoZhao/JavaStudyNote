package yc.no4;

import my_code_sum_up.code_linkedlist.util.ListNode;

public class Test237 {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
