package yc.al.zuo.base;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 介绍二叉树的序列化和反序列化
 * @author code_yc
 * @version 1.0
 * @date 2020/4/24 15:53
 */
public class Class4_Code4_SerializeAndReconstructTree {

    // 创建节点类
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 按前序遍历的方式序列化二叉树
    public static String serialByPre(Node head){
        if(head == null){
            return "#!";
        }
        String res = "";
        res += head.value + "!";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    // 按前序遍历的字符串反序列化
    public static Node reconByPreString(String preStr){
        String[] chs = preStr.split("!");
        Queue<String> queue = new LinkedList<>();
        // 将划分出来的字符串放入队列中
        for (String ch : chs) {
            queue.add(ch);
        }
        // 根据队列生成二叉树
        return reconPreOrder(queue);
    }

    private static Node reconPreOrder(Queue<String> queue) {
        String str = queue.poll();
        if("#".equals(str)){
            return null;
        }
        Node root = new Node(Integer.parseInt(str));
        root.left = reconPreOrder(queue);
        root.right = reconPreOrder(queue);
        return root;
    }

    // 按层序列化字符串
    public static String serialByLevel(Node head) {
        if(head == null) return "#!";
        String res = head.value + "!";
        // 借助队列，一层依次的遍历添加元素
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(head);
        while(!queue.isEmpty()){
            head = queue.poll();
            if(head.left != null){
                res += head.left.value + "!";
                queue.add(head.left);
            }else{
                res += "#!";
            }
            if(head.right != null){
                res += head.right.value + "!";
                queue.add(head.right);
            }else{
                res += "#!";
            }
        }
        return res;
    }

    // 按层反序列化
    public static Node reconByLevelString(String levelStr) {
        String[] values = levelStr.split("!");
        Queue<Node> queue = new LinkedList<>();
        int index = 0;
        Node head = generateNodeByString(values[index++]);
        if(head != null){
            queue.add(head);
        }
        Node node = null;
        while(!queue.isEmpty()){
            node = queue.poll();
            node.left = generateNodeByString(values[index++]);
            node.right = generateNodeByString(values[index++]);
            if(node.left != null){
                queue.add(node.left);
            }
            if(node.right != null){
                queue.add(node.right);
            }
        }
        return head;
    }

    private static Node generateNodeByString(String value){
        if(value.equals("#")){
            return null;
        }
        return new Node(Integer.parseInt(value));
    }

    // 测试
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    // for test -- print tree（打印树）
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }


    public static void main(String[] args) {
        //=========================测试空树================================
        Node head = null;
        printTree(head);
        String pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        String level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

        //========================测试非空树=============================
        head = new Node(1);
        printTree(head);

        pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

        //=========================测试节点较多的非空树=====================
        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.right = new Node(5);
        printTree(head);

        pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

        //==============================再测试一棵树=================================
        head = new Node(100);
        head.left = new Node(21);
        head.left.left = new Node(37);
        head.right = new Node(-42);
        head.right.left = new Node(0);
        head.right.right = new Node(666);
        printTree(head);

        pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");
        //=========================结束测试==========================================
    }
}
