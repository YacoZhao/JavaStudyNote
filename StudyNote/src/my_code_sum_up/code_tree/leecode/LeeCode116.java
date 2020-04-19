package my_code_sum_up.code_tree.leecode;


import java.util.LinkedList;
import java.util.Queue;

//116. 填充每个节点的下一个右侧节点指针
public class LeeCode116 {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    // 解法一： 自己写
    public Node connect(Node root) {
        if(root == null || root.left == null) return root;
        Queue<Node> queue = new LinkedList<Node>();
        Node cur = root;
        queue.add(cur);
        while(!queue.isEmpty()){
            cur = queue.poll();
            if(cur.left != null){
                queue.add(cur.left);
                queue.add(cur.right);
            }
            if(!queue.isEmpty()){
                cur.next = queue.peek();
            }
        }
        cur = root;
        while(cur.right != null){
            cur.next = null;
            cur = cur.right;
        }
        return root;
    }

    //解法二
    public Node connect2(Node root) {
        if(root == null || root.left == null) return root;
        Queue<Node> queue = new LinkedList<Node>();
        Node cur = root;
        queue.add(cur);
        while(!queue.isEmpty()){
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                cur = queue.poll();
                if(i < size - 1){
                    cur.next = queue.peek();
                }
                if(cur.left != null){
                    queue.add(cur.left);
                    queue.add(cur.right);
                }
            }
        }
        return root;
    }


    // 解法三：利用额外空闲指针
    public Node connect3(Node root) {
        if(root == null) return null;
        Node temp = root;
        while(temp.left != null){
            Node head = temp;
            while(head != null){
                head.left.next = head.right;
                if(head.next != null){
                    head.right.next = head.next.right;
                }
                head = head.next;
            }
            temp = temp.left;
        }
        return root;
    }

}
