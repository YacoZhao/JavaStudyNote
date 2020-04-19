package yc.third;

import my_code_sum_up.code_linkedlist.util.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Test142 {

    /**
     * 采用set辅助搜索重复项
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if(head == null){
            return null;
        }
        Set<ListNode> set = new HashSet<>();
        while(head != null){
            if(set.contains(head)){
                return head;
            }else{
                set.add(head);
            }
            head = head.next;
        }
        return null;
    }
}
