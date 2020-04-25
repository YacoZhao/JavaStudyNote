package yc.al.zuo.base;

/**
 * 找到一个节点的后继节点
 * 后继节点的定义： 在一颗二叉树中，按照中序遍历的方式，某个节点的下一个节点称为该节点的后继节点
 * @author code_yc
 * @version 1.0
 * @date 2020/4/24 15:35
 */
public class Class4_Code2_SuccessorNode {

    // 定义节点类
    static class Node{
        int value;
        Node left;
        Node right;
        Node parent;

        public Node(int value) {
            this.value = value;
        }
    }

    // 返回node节点的后继节点
    public static Node getSuccessorNode(Node node){
        if(node == null) return null;
        // 如果当前节点有右孩子，则后继节点就是右子树中最小的孩子
        if(node.right != null){
            Node cur = node.right;
            while(cur.left != null){
                cur = cur.left;
            }
            return cur;
        }
        // 向上找父节点
        Node parent = node.parent;
        while(parent != null && parent.left != node){
            node = parent;
            parent = node.parent;
        }
        return parent;
    }

    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //                        测试函数
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getSuccessorNode(test));
    }
}
