package my_code_sum_up.code_tree.zuo;

import my_code_sum_up.code_tree.util.Node;

/**
 * 左神基础课————后继节点
 */
public class Zuo_SuccessorNode {

    /**
     * 查找当前节点的后继节点
     *
     * @param node
     * @return
     */
    public static Node successorNode(Node node) {
        if (node == null) return null;
        // 如果存在右子树，则返回右子树中的最小值
        if (node.right != null) {
            node = node.right;
            while(node.left != null){
                node = node.left;
            }
            return node;
        }
        //如果不存在右子树，则向上找到第一个当前节点是该节点父节点的左孩子
        Node parent = node.parent;
        while (parent != null && parent.left != node) {
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }

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
        System.out.println(test.value + " next: " + successorNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + successorNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + successorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + successorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + successorNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + successorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + successorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + successorNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + successorNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + successorNode(test));
    }
}
