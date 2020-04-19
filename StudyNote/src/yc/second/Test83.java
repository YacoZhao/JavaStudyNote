package yc.second;

import my_code_sum_up.code_linkedlist.util.ListNode;

public class Test83 {
    /**
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        //1. 如果节点为空，或者只有一个有效节点，那么直接返回head
        //2. 设置哑节点指向head
        //3. 设置temp节点遍历：
            //3.1 如果temp.val == temp.next.val: temp.next = temp.next.next, temp保持不动
            //3.2 如果temp.val != temp.next.val: temp = temp.next;

        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode temp = head;
        while (temp != null) {
            if (temp.next != null && temp.next.val == temp.val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return dummy.next;
    }
}
