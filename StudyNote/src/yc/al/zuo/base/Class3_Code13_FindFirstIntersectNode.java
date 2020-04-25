package yc.al.zuo.base;

/**
 * 寻找两个链表相交的第一个节点
 * @author code_yc
 * @version 1.0
 * @date 2020/4/24 11:23
 */
public class Class3_Code13_FindFirstIntersectNode {


    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        //1. 如果head1或者head2其中有一个为空节点的话，则肯定没有相交的节点
        if(head1 == null || head2 == null) return null;
        //2. 检查链表1和链表2是否有环结构，有则返回入环节点，没有则返回null
        Node loop1 = getLoopNde(head1);
        Node loop2 = getLoopNde(head2);
        //3. 如果两条链表都没有环（操作如下）
        if(loop1 == null && loop2 == null) {
            return noLoop(head1,head2);
        }
        if(loop1 != null && loop2 != null) {
            return bothLoop(head1,loop1,head2,loop2);
        }
        // 当一个有环一个没有环，则一定不可能有交点
        return null;
    }

    /**
     * 检查两个都有环的链表的相交节点
     * @param head1
     * @param loop1
     * @param head2
     * @param loop2
     * @return
     */
    private static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        if(loop1 == loop2){
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while(cur1 != loop1){
                n++;
                cur1 = cur1.next;
            }
            while(cur2 != loop2){
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while(n != 0){
                n--;
                cur1 = cur1.next;
            }
            while(cur1 != cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }else{
            cur1 = loop1.next;
            while(cur1 != loop1){
                if(cur1 == loop2){
                    return loop1;
                }
                cur1 =cur1.next;
            }
            return null;
        }
    }

    /**
     * 检查两个无环结构的链表相交节点
     * @param head1
     * @param head2
     * @return
     */
    private static Node noLoop(Node head1, Node head2) {
        if(head1 == null || head2 == null) return null;
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while(cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while(cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        // 如果两个链表的最后一个节点不相等，则一定不会有相交的节点
        if(cur1 != cur2) {
            return null;
        }
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        // 让较长的链表先走一段长度，使得两个链表一样长，然后再同时走，第一个相等的节点即为相交节点
        while(n != 0){
            n--;
            cur1 = cur1.next;
        }
        while(cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    /**
     * 找出入环节点，找到则返回节点，没找到则返回null
     * @param head
     * @return
     */
    private static Node getLoopNde(Node head) {
        if(head == null || head.next == null || head.next.next == null) return null;
        Node slow = head.next;
        Node fast = head.next.next;
        while(slow != fast){
            if(fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while(fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);
    }

}
