package yc.third;

        import my_code_sum_up.code_linkedlist.util.ListNode;

public class Test143 {
    public void reorderList(ListNode head) {
        //边界条件判断
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        //快慢指针找出链表的中心节点
        ListNode mid_node = findMidNode(head);
        //将链表分为左右两个部分
        ListNode right_list = mid_node.next;
        mid_node.next = null;
        ListNode left_list = head;
        //将右边的链表反转
        right_list = resverseList(right_list);
        //合并两个单链表
        merger(left_list, right_list);
    }

    private ListNode findMidNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode resverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 将head的后面的元素先进行反转
        ListNode last = resverseList(head.next);
        // 然后向head指向下一个节点
        head.next.next = head;
        head.next = null;
        return last;
    }

    private void merger(ListNode left, ListNode right) {
        //先把left和right的下一个节点都存储下来
        ListNode left_next;
        ListNode right_next;
        while (left.next != null && right != null) {
            left_next = left.next;
            right_next = right.next;

            left.next = right;
            right.next = left_next;

            left = left_next;
            right = right_next;
        }
    }
}
