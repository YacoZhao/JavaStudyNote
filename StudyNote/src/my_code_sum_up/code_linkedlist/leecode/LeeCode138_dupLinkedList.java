package my_code_sum_up.code_linkedlist.leecode;

import yc.third.Node;

import java.util.HashMap;

/**
 *
 * 复制带random的列表——————左基础课原题
 */
public class LeeCode138_dupLinkedList {

    public Node copyRandomList(Node head) {
        // 构建哈希表
        HashMap<Node,Node> map = new HashMap<Node,Node>();

        Node cur = head;

        while(cur != null){
            map.put(cur,new Node(cur.val,null,null));
            cur = cur.next;
        }

        cur = head;
        while(cur != null){
            map.get(cur).next = cur.next != null ? map.get(cur.next) : null;
            map.get(cur).random = cur.random != null ? map.get(cur.random) : null;
            cur = cur.next;
        }

        return map.get(head);
    }
}
