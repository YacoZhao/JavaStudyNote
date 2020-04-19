package yc.third;

import my_code_sum_up.code_linkedlist.util.ListNode;

import java.util.HashSet;

public class Test141 {
    /**
     * 采用HashSet记录添加的节点，如果重复添加了，则一定有环
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        //创建HashSet
        HashSet<ListNode> set = new HashSet<>();
        while (head != null) {
            boolean flag = set.add(head);
            if (!flag) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    /**
     * 双指针求解的快速算法
     * @param head
     * @return
     */
    public boolean hasCycle2(ListNode head) {
        //首先进行边界条件判断
        if (head == null) {
            return false;
        }
        //定义快慢指针
        ListNode slow = head;
        ListNode fast = head.next;
        //循环遍历
        while (slow != fast) {
            if (slow == null || fast == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

}
